package com.gasolinecalculation.navigation

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Forward


class FlowRouter(val router: Router) : Router() {

    fun startFlow(screen: Screen) {
        router.navigateTo(screen)
    }

    fun newRootFlow(screen: Screen) {
        router.newRootScreen(screen)
    }

    fun finishFlow() {
        router.exit()
    }

    fun newScreenChain(screen: Screen) {
        executeCommands(
            BackTo(null),
            Forward(screen)
        )
    }

}