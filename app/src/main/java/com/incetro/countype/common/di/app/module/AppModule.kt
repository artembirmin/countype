package com.incetro.countype.common.di.app.module

import android.app.Application
import android.content.Context
import com.incetro.countypecore.calculator.Calculator
import com.incetro.countypecore.calculator.CalculatorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    fun provideCalculator(context: Context): Calculator {
        return CalculatorImpl(context.resources)
    }
}