/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 5/3/2022.
 */

package com.incetro.countype.app

import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.common.navigation.Screens
import com.incetro.countype.data.api.CountypeApi
import com.incetro.countype.data.database.city.CityDao
import com.incetro.countype.data.database.city.CityDto
import com.incetro.countype.data.database.datestamp.DatestampDao
import com.incetro.countype.data.database.datestamp.DatestampDto
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDao
import com.incetro.countype.data.database.measure.MeasureDao
import com.incetro.countype.data.database.timestamp.TimestampDao
import com.incetro.countype.data.database.timestamp.TimestampDto
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countypecore.calculator.Calculator
import com.incetro.countypecore.calculator.CalculatorConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLauncher @Inject constructor(
    private val router: AppRouter,
    private val noteRepository: NoteRepository,
    private val countypeApi: CountypeApi,
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
        val functionDescriptionRequest = countypeApi.getFunctionDescriptions()
            .flatMapCompletable { functionDescriptionDao.insertAll(it) }
            .andThen(Single.defer { functionDescriptionDao.getAll() })
            .map { it.map { dto -> dto.toFunctionDescription() } }

        val measureRequest = countypeApi.getMeasures()
            .flatMapCompletable { measureDao.insertAll(it) }
            .andThen(Single.defer { measureDao.getAll() })
            .map { it.map { dto -> dto.toMeasure() } }

        val datestampRequest = countypeApi.getDatestamps()
            .flatMapCompletable { datestampDao.insertAll(it) }
            .andThen(Single.defer { datestampDao.getAll() })
            .onErrorResumeWith { emptyList<DatestampDto>() }
            .map { it.map { dto -> dto.toDatestamp() } }

        val timestampRequest = countypeApi.getTimestamps()
            .flatMapCompletable { timestampDao.insertAll(it) }
            .andThen(Single.defer { timestampDao.getAll() })
            .onErrorResumeWith { emptyList<TimestampDto>() }
            .map { it.map { dto -> dto.toTimestamp() } }

        val cityRequest = countypeApi.getCities()
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