package com.gasolinecalculation

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.di.modules.ServerModule
import com.gasolinecalculation.di.modules.appModule
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initToothpick()
        initAppScope()
    }

    @VisibleForTesting
    fun initAppScope() {
        appScope = Toothpick.openScope(DI.APP_SCOPE)
        appScope.installModules(
            appModule(this)
        )

        val serverScope = Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE)
        serverScope.installModules(
            ServerModule()
        )

    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().preventMultipleRootScopes())
        }
    }


    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        appScope.release()
    }
}