/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 15/2/2022.
 */

package com.incetro.countype.di.app.module

import com.incetro.countype.common.navigation.AppRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Cicerone.create
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone: Cicerone<AppRouter> = create(AppRouter())

    @Provides
    @Singleton
    fun provideRouter(): AppRouter {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}