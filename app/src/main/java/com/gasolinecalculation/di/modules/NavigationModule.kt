package com.gasolinecalculation.di.modules

import com.gasolinecalculation.navigation.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class NavigationModule(router: Router) : Module() {
    init {
        val cicerone = Cicerone.create(FlowRouter(router))
        bind(FlowRouter::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}
