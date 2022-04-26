package com.incetro.countype.di.app.module

import android.app.Application
import android.content.Context
import com.incetro.countype.app.AppLauncher
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.data.repository.NoteRepository
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
    fun provideAppLauncher(
        router: AppRouter,
        noteRepository: NoteRepository
    ): AppLauncher {
        return AppLauncher(router, noteRepository)
    }
}