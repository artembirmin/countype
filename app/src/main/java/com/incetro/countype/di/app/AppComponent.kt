package com.incetro.countype.di.app

import android.content.Context
import com.incetro.countype.app.App
import com.incetro.countype.app.AppLauncher
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countype.di.app.module.AppModule
import com.incetro.countype.di.app.module.DatabaseModule
import com.incetro.countype.di.app.module.NavigationModule
import com.incetro.countype.di.app.module.RepositoryModule
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NavigationModule::class,
        DatabaseModule::class,
        RepositoryModule::class]
)
interface AppComponent {

    fun inject(app: App)

    // AppModule
    fun getContext(): Context
    fun provideAppLauncher(): AppLauncher

    // NavigationModule
    fun provideAppRouter(): AppRouter
    fun provideNavigationHolder(): NavigatorHolder

    // Repository module
    fun provideNoteRepository(): NoteRepository

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}