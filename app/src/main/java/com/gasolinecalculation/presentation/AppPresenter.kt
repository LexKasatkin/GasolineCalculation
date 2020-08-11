package com.gasolinecalculation.presentation

import com.gasolinecalculation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AppPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<MvpView>() {

    /**
     * Cold start of application.
     */
    fun coldStart() {
        val rootScreen = Screens.Splash
        router.newRootChain(rootScreen)
    }
}