package com.incetro.countype.di.recordslist

import android.content.Context
import com.incetro.countype.di.common.scope.PerFeature
import com.incetro.countype.presentation.note.interactor.RecordsListInteractor
import com.incetro.countype.presentation.note.interactor.RecordsListInteractorImpl
import com.incetro.countype.presentation.note.presenter.RecordsListPresenter
import com.incetro.countype.presentation.note.presenter.RecordsListPresenterImpl
import com.incetro.countypecore.calculator.Calculator
import com.incetro.countypecore.calculator.CalculatorImpl
import dagger.Module
import dagger.Provides

@Module
class RecordsListModule {
    @PerFeature
    @Provides
    fun provideRecordsPresenter(recordsListInteractor: RecordsListInteractor): RecordsListPresenter {
        return RecordsListPresenterImpl(recordsListInteractor)
    }

    @PerFeature
    @Provides
    fun provideRecordsInteractor(calculator: Calculator): RecordsListInteractor {
        return RecordsListInteractorImpl(calculator)
    }

    @Provides
    @PerFeature
    fun provideCalculator(context: Context): Calculator {
        return CalculatorImpl(context.resources)
    }
}