package com.incetro.countype.common.di.note

import android.content.Context
import com.incetro.countype.common.di.scope.FeatureScope
import com.incetro.countype.presentation.note.interactor.NoteInteractor
import com.incetro.countype.presentation.note.interactor.NoteInteractorImpl
import com.incetro.countype.presentation.note.presenter.NotePresenter
import com.incetro.countype.presentation.note.presenter.NotePresenterImpl
import com.incetro.countypecore.calculator.Calculator
import com.incetro.countypecore.calculator.CalculatorImpl
import dagger.Module
import dagger.Provides

@Module
class NoteModule {
    @FeatureScope
    @Provides
    fun provideRecordsPresenter(noteInteractor: NoteInteractor): NotePresenter {
        return NotePresenterImpl(noteInteractor)
    }

    @FeatureScope
    @Provides
    fun provideRecordsInteractor(calculator: Calculator): NoteInteractor {
        return NoteInteractorImpl(calculator)
    }

    @Provides
    @FeatureScope
    fun provideCalculator(context: Context): Calculator {
        return CalculatorImpl(context.resources)
    }
}