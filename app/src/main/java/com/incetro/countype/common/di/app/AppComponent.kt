/*
 * My Application
 *
 * Created by artembirmin on 10/5/2022.
 */

package com.incetro.countype.common.di.app

import android.content.Context
import com.incetro.countype.app.App
import com.incetro.countype.app.AppLauncher
import com.incetro.countype.common.di.app.module.*
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.data.api.CountypeApi
import com.incetro.countype.data.database.city.CityDao
import com.incetro.countype.data.database.datestamp.DatestampDao
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDao
import com.incetro.countype.data.database.measure.MeasureDao
import com.incetro.countype.data.database.note.NoteDao
import com.incetro.countype.data.database.timestamp.TimestampDao
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countypecore.calculator.Calculator
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NavigationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class]
)
interface AppComponent {

    fun inject(app: App)

    // AppModule
    fun getContext(): Context
    fun provideAppLauncher(): AppLauncher
    fun provideCalculator(): Calculator

    // NavigationModule
    fun provideAppRouter(): AppRouter
    fun provideNavigationHolder(): NavigatorHolder

    // Repository module
    fun provideNoteRepository(): NoteRepository

    fun provideCountypeApi(): CountypeApi
    fun noteDao(): NoteDao
    fun functionDescriptionDao(): FunctionDescriptionDao
    fun measureDao(): MeasureDao
    fun cityDao(): CityDao
    fun timestampDao(): TimestampDao
    fun datestampDao(): DatestampDao

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}