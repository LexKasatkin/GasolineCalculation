package com.gasolinecalculation

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.di.appModule
import com.gasolinecalculation.di.networkModule
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    lateinit var scope: Scope

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        } else {
        }

        initToothpick()
        initAppScope(Toothpick.openScope(DI.APP_SCOPE))
    }

    @VisibleForTesting
    fun initAppScope(appScope: Scope) {
        appScope.installModules(
            appModule(this)
        )

        val serverScope = Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE)
        serverScope.installModules(
            networkModule()
        )

    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().preventMultipleRootScopes())
        }
    }

//    override fun onTrimMemory(level: Int) {
//        super.onTrimMemory(level)
//        scope.release()
//    }
}