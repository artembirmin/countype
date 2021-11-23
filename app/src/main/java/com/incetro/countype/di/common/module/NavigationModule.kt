package com.incetro.countype.di.common.module

import com.incetro.countype.common.AppRouter
import com.incetro.countype.di.app.activity.PerActivity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

@Module
class NavigationModule {
    private val cicerone: Cicerone<AppRouter> =
        Cicerone.create(AppRouter())

    @Provides
    @PerActivity
    fun provideMainRouter(): AppRouter = cicerone.router

    @Provides
    @PerActivity
    fun provideMainNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder
}