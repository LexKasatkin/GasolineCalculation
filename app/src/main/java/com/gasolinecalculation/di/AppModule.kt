package com.gasolinecalculation.di

import android.app.Application
import android.content.Context
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.ktp.binding.module

fun appModule(application: Application) = module {
    //Global
    val context = application.applicationContext
    bind(Application::class.java).toInstance(application)
    bind(Context::class.java).toInstance(context)

    // Navigation
    val cicerone = Cicerone.create()
    bind(Router::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
}