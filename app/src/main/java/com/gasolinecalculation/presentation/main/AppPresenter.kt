package com.gasolinecalculation.presentation.main

import com.gasolinecalculation.Screens
import com.gasolinecalculation.domain.interactors.AppInteractor
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppPresenter @Inject constructor(
    private val router: Router,
    private val appInteractor: AppInteractor
) {

    fun onLaunch() {
//        appInteractor.signInToLastSession()
    }

    fun coldStart() {
        val rootScreen =
//            if (appInteractor.hasAccount)
            Screens.TopFlow
//            else Screens.AuthFlow

//        if (appInteractor.isFirstLaunch) {
        router.newRootChain(rootScreen)
//        } else {
//            router.newRootScreen(rootScreen)
//        }
    }
}
