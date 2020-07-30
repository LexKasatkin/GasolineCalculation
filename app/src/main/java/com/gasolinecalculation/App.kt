package com.invest.altair

import android.app.Application
import com.invest.altair.di.ApplicationScope
import timber.log.Timber
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.module

class App : Application() {

    private val appModule = module {
        // We make the modules available for injection
        bind<Module>().withName(ActivityModule::class).toInstance(activityModule)
        bind<Module>().withName(ActivityVMModule::class).toInstance(activityVMModule)
        bind<Module>().withName(FragmentModule::class).toInstance(fragmentModule)
        bind<Module>().withName(FragmentVMModule::class).toInstance(fragmentVMModule)
        // TODO: Other application-scope custom bindings can go here.
    }

    lateinit var scope: Scope

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        } else {
//            Timber.plant(CrashReportingTree());
        }

        scope = KTP
            .openScope(ApplicationScope::class.java)
            .installModules(SmoothieApplicationModule(this), appModule)
        scope.inject(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        scope.release()
    }
}