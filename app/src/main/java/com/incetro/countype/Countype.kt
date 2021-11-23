package com.incetro.countype

import android.app.Application
import com.incetro.countype.di.app.AppComponent
import com.incetro.countype.di.app.AppModule
import com.incetro.countype.di.app.DaggerAppComponent

class Countype : Application() {

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}