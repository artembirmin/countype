package com.incetro.countype.common.di.activity

import android.content.Context
import com.incetro.countype.app.AppActivity
import com.incetro.countype.common.di.app.component.AppComponent
import com.incetro.countype.common.di.componentmanager.ComponentManager
import com.incetro.countype.common.di.componentmanager.ComponentsManager
import com.incetro.countype.common.di.scope.ActivityScope
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.data.repository.NoteRepository
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(appActivity: AppActivity)

    fun getContext(): Context

    // NavigationModule from AppComponent
    fun getAppRouter(): AppRouter
    fun getNavigationHolder(): NavigatorHolder

    fun provideNoteRepository(): NoteRepository

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