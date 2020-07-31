package com.gasolinecalculation.di

import com.gasolinecalculation.navigation.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

fun module(func: (Module.() -> (Unit))) = object : Module() {
    init {
        func()
    }
}

fun flowModule(appRouter: Router) = module {
    val cicerone = Cicerone.create(FlowRouter(appRouter))
    bind(FlowRouter::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
}