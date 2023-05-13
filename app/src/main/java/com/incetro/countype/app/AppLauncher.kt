/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 5/3/2022.
 */

package com.incetro.countype.app

import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.common.navigation.Screens
import com.incetro.countype.data.api.ServerApi
import com.incetro.countype.data.database.city.CityDao
import com.incetro.countype.data.database.city.CityDto
import com.incetro.countype.data.database.datestamp.DatestampDao
import com.incetro.countype.data.database.datestamp.DatestampDto
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDao
import com.incetro.countype.data.database.measure.MeasureDao
import com.incetro.countype.data.database.timestamp.TimestampDao
import com.incetro.countype.data.database.timestamp.TimestampDto
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import com.incetro.countype.presentation.toReadableDateTime
import com.incetro.countypecore.calculator.Calculator
import com.incetro.countypecore.calculator.CalculatorConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import org.joda.time.PeriodType
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLauncher @Inject constructor(
    private val router: AppRouter,
    private val noteRepository: NoteRepository,
    private val serverApi: ServerApi,
    private val functionDescriptionDao: FunctionDescriptionDao,
    private val measureDao: MeasureDao,
    private val cityDao: CityDao,
    private val datestampDao: DatestampDao,
    private val timestampDao: TimestampDao,
    val calculator: Calculator,
) {

    var calculatorSetupDisposable: Disposable? = null

    /**
     *  Initialized and launches application.
     */
    fun start() {
        val functionDescriptionRequest = serverApi.getFunctionDescriptions()
            .flatMapCompletable { functionDescriptionDao.insertAll(it) }
            .andThen(Single.defer { functionDescriptionDao.getAll() })
            .map { it.map { dto -> dto.toFunctionDescription() } }

        val measureRequest = serverApi.getMeasures()
            .flatMapCompletable { measureDao.insertAll(it) }
            .andThen(Single.defer { measureDao.getAll() })
            .map { it.map { dto -> dto.toMeasure() } }

        val datestampRequest = serverApi.getDatestamps()
            .flatMapCompletable { datestampDao.insertAll(it) }
            .andThen(Single.defer { datestampDao.getAll() })
            .onErrorResumeWith { emptyList<DatestampDto>() }
            .map { it.map { dto -> dto.toDatestamp() } }

        val timestampRequest = serverApi.getTimestamps()
            .flatMapCompletable { timestampDao.insertAll(it) }
            .andThen(Single.defer { timestampDao.getAll() })
            .onErrorResumeWith { emptyList<TimestampDto>() }
            .map { it.map { dto -> dto.toTimestamp() } }

        val cityRequest = serverApi.getCities()
            .flatMapCompletable { cityDao.insertAll(it) }
            .andThen(Single.defer { cityDao.getAll() })
            .onErrorResumeWith { emptyList<CityDto>() }
            .map { it.map { dto -> dto.toCity() } }

        calculatorSetupDisposable = Single.zip(
            functionDescriptionRequest,
            measureRequest,
            cityRequest,
            datestampRequest,
            timestampRequest
        ) { a, b, c, d, e ->
            CalculatorConfig(a, b, c, d, e)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ calculator.setupData(it) }, { Timber.e(it) })

        val firstNote = Note(
            id = UUID.randomUUID().toString(),
            name = "Общая",
            lastUpdateTime = DateTime.now().minusHours(2).minusMinutes(23).toDate(),
            records = listOf(
                Record(
                    position = 1,
                    phrase = "Cлучайное число между 11 и 23",
                    answer = "15"
                ),
                Record(
                    position = 2,
                    phrase = "Среднее между 134 и 464",
                    answer = "299"
                ),
                Record(
                    position = 3,
                    phrase = "Меньшее из 13604969349 и 136049746546",
                    answer = "13 604 969 349 "
                ),
                Record(
                    position = 4,
                    phrase = "Большее из 13604969349 и 136049746546",
                    answer = "136 049 746 546"
                ),
                Record(
                    position = 5,
                    phrase = "Половина от 13",
                    answer = "7.5"
                ),
            )
        )
        val secondNote = Note(
            id = UUID.randomUUID().toString(),
            name = "Проценты",
            lastUpdateTime = DateTime.now().minusHours(1).minusMinutes(15).toDate(),
            records = listOf(
                Record(
                    position = 1,
                    phrase = "Налог 13 процентов от 900 тысяч",
                    answer = "177"
                ),
                Record(
                    position = 2,
                    phrase = "117 это 13 процентов от чего",
                    answer = "900"
                ),
                Record(
                    position = 3,
                    phrase = "13% от чего равно 117",
                    answer = "900"
                ),
                Record(
                    position = 4,
                    phrase = "Добавить 13% к 900",
                    answer = "1 017"
                ),
                Record(
                    position = 5,
                    phrase = "783 это 13% отняли от",
                    answer = "900"
                ),
                Record(
                    position = 6,
                    phrase = "40% к чему равно 900",
                    answer = "642.85"
                ),
                Record(
                    position = 7,
                    phrase = "177 это сколько % от 900",
                    answer = "13%"
                ),
                Record(
                    position = 8,
                    phrase = "1300 это сколько % добавили к 200",
                    answer = "550%"
                ),
                Record(
                    position = 9,
                    phrase = "723 это сколько % отняли от 900",
                    answer = "13%"
                ),
            )
        )
        val theedNote = Note(
            id = UUID.randomUUID().toString(),
            name = "Время и даты",
            lastUpdateTime = DateTime.now().minusHours(0).minusMinutes(42).toDate(),
            records = listOf(
                Record(
                    position = 1,
                    phrase = "Дни от 11-05-2023 до 23-06-2023",
                    answer = "43"
                ),
                Record(
                    position = 2,
                    phrase = "43 дня в часах",
                    answer = "1 032"
                ),
                Record(
                    position = 3,
                    phrase = "Дни до 23-06-2023",
                    answer = Period(
                        DateTime.now(),
                        DateTime(2023, 6, 23, 0, 0),
                        PeriodType.days())
                        .days.plus(1).toString()
                ),
                Record(
                    position = 4,
                    phrase = "Часы до 23:00",
                    answer = Period(DateTime.now(),
                        DateTime.now().withTime(23, 0, 0, 0),
                        PeriodType.hours())
                        .hours.toString()
                ),
                Record(
                    position = 5,
                    phrase = "13 часов назад",
                    answer = DateTime.now().minusHours(13).toReadableDateTime()
                ),
                Record(
                    position = 6,
                    phrase = "13 дней назад",
                    answer = DateTime.now().minusDays(13).toReadableDateTime()
                ),
                Record(
                    position = 7,
                    phrase = "Дни с 01-01-2023",
                    answer = Period(
                        DateTime(2023, 1, 1, 0, 0),
                        DateTime.now(), PeriodType.days())
                        .days.plus(1).toString()
                ),
                Record(
                    position = 8,
                    phrase = "Время в Баку",
                    answer = DateTime.now(DateTimeZone.forID("Asia/Baku")).toReadableDateTime()
                ),
                Record(
                    position = 9,
                    phrase = "Время в Иркутске",
                    answer = DateTime.now(DateTimeZone.forID("Asia/Irkutsk")).toReadableDateTime()
                ),
                Record(
                    position = 10,
                    phrase = "Разница во времени между Иркутском и Баку",
                    answer = "-4 часа"
                ),
            )
        )
        val forthNote = Note(
            id = UUID.randomUUID().toString(),
            name = "Единицы измерения и пропорции",
            lastUpdateTime = DateTime.now().minusHours(0).minusMinutes(42).toDate(),
            records = listOf(
                Record(
                    position = 1,
                    phrase = "Сколько мегагерц в 4.4 гигагерцах",
                    answer = "4 400"
                ),
                Record(
                    position = 2,
                    phrase = "6 футов в метрах",
                    answer = "1.83"
                ),
                Record(
                    position = 3,
                    phrase = "40 гектар в квадратных метрах",
                    answer = "400 000"
                ),
                Record(
                    position = 4,
                    phrase = "1 к 4 это как что к 350",
                    answer = "87.5"
                ),
                Record(
                    position = 5,
                    phrase = "1 к 4 это 120 к",
                    answer = "480"
                ),

                )
        )

        Completable.concatArray(*listOf(firstNote,
            secondNote,
            theedNote,
            forthNote).map { noteRepository.addNote(it) }.toTypedArray())
            .subscribeOn(Schedulers.io())
            .subscribe()
//        repository.addNote(
//            Note(
//                id = Constants.ROOT_NOTE_ID,
//                name = Constants.ROOT_NOTE_NAME,
//                lastUpdateTime = Date(),
//                records = listOf(
//                    Record(
//                        id = UUID.randomUUID().toString(),
//                        position = 1,
//                        phrase = "",
//                        answer = ""
//                    )
//                )
//            )
//        ).subscribeOn(Schedulers.io())
//            .subscribe()
        router.newRootScreen(
            Screens.NoteListScreen
        )
    }
}