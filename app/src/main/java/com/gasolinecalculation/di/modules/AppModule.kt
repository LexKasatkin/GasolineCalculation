package com.gasolinecalculation.di.modules

import android.app.Application
import android.content.Context
import com.gasolinecalculation.common.Prefs
import com.gasolinecalculation.system.ResourceManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.ktp.binding.module

fun appModule(application: Application) = module {
    //Global
    val context = application.applicationContext
    bind(Application::class.java).toInstance(application)
    bind(Context::class.java).toInstance(context)
    bind(ResourceManager::class.java).singleton()
    bind(Prefs::class.java).singleton()

    // Navigation
    val cicerone = Cicerone.create()
    bind(Router::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
}