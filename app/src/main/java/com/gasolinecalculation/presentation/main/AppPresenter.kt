package com.gasolinecalculation.presentation.main

import com.gasolinecalculation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AppPresenter @Inject constructor(
    private val router: Router
//    private val appInteractor: AppInteractor
) : MvpPresenter<MvpView>() {


    fun onLaunch() {
//        appInteractor.signInToLastSession()
    }

    fun coldStart() {
        val rootScreen =
//            if (appInteractor.hasAccount)
            Screens.Splash
//            else Screens.AuthFlow

//        if (appInteractor.isFirstLaunch) {
//        router.newRootChain(rootScreen)
//        } else {
//            router.newRootScreen(rootScreen)
//        }
    }
}