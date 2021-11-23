package com.incetro.countype.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.incetro.countype.Countype
import com.incetro.countype.R
import com.incetro.countype.di.app.activity.ActivityComponent
import com.incetro.countype.di.app.activity.DaggerActivityComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AppActivity : AppCompatActivity() {

    lateinit var appRouter: AppRouter

    private val cicerone: Cicerone<AppRouter> = Cicerone.create(AppRouter())

    private val navigatorHolder: NavigatorHolder = cicerone.navigatorHolder

    private val navigator: Navigator = SupportAppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        appRouter = cicerone.router
        appRouter.newRootScreen(Screens.RecyclerViewScreen)
    }

    private fun inject() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent(Countype.appComponent) //KOLKHOZ
            .build()
        activityComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    companion object {
        lateinit var activityComponent: ActivityComponent
    }
}