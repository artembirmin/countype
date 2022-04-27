package com.incetro.countype.app

import android.app.Application
import com.incetro.countype.common.di.componentmanager.ComponentsManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        ComponentsManager.init(this)
        ComponentsManager.getInstance().applicationComponent.inject(this)
    }
}