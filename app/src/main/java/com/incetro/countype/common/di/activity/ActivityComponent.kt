package com.incetro.countype.common.di.activity

import android.content.Context
import com.incetro.countype.app.AppActivity
import com.incetro.countype.app.componentmanager.ComponentManager
import com.incetro.countype.app.componentmanager.ComponentsManager
import com.incetro.countype.common.di.app.AppComponent
import com.incetro.countype.common.di.scope.ActivityScope
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.data.api.ServerApi
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

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(appActivity: AppActivity)

    fun getContext(): Context

    fun provideCalculator(): Calculator

    // NavigationModule from AppComponent
    fun getAppRouter(): AppRouter
    fun getNavigationHolder(): NavigatorHolder

    fun provideNoteRepository(): NoteRepository

    fun provideCountypeApi(): ServerApi
    fun noteDao(): NoteDao
    fun functionDescriptionDao(): FunctionDescriptionDao
    fun measureDao(): MeasureDao
    fun cityDao(): CityDao
    fun timestampDao(): TimestampDao
    fun datestampDao(): DatestampDao

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): ActivityComponent
    }

    object Manager : ComponentManager<ActivityComponent>(
        clazz = ActivityComponent::class.java,
        createAndSave = {
            val componentManager = ComponentsManager.getInstance()
            val activityComponent = DaggerActivityComponent.builder()
                .appComponent(componentManager.applicationComponent)
                .build()

            componentManager.addComponent(activityComponent)
        })
}