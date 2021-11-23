package com.incetro.countype.di.app

import android.app.Application
import android.content.Context
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
}