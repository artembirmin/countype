package com.incetro.countype.app

import android.app.Application
import com.incetro.countype.app.componentmanager.ComponentsManager
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        inject()

        Timber.plant(Timber.DebugTree())
    }

    private fun inject() {
        ComponentsManager.init(this)
        ComponentsManager.getInstance().applicationComponent.inject(this)
    }
}