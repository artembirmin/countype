package com.incetro.countype.di.app.activity

import android.content.Context
import com.incetro.countype.common.AppActivity
import com.incetro.countype.di.app.AppComponent
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(appActivity: AppActivity)

    fun getContext(): Context

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): ActivityComponent
    }
}