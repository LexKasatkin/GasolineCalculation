package com.gasolinecalculation.presentation

import com.gasolinecalculation.Screens
import com.gasolinecalculation.domain.interactors.AppInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@StateStrategyType(SingleStateStrategy::class)
interface SplashView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun showError(msg: String)
}

@InjectViewState
class SplashPresenter @Inject constructor(
    val router: Router,
    private val appInteractor: AppInteractor
) : MvpPresenter<SplashView>() {

    override fun onFirstViewAttach() {
        start()
    }

    private fun start() {
        GlobalScope.launch(Dispatchers.Main) {
            viewState.showProgress()
            delay(1000)
            toNextScreen()
            viewState.hideProgress()
        }
    }

    private fun toNextScreen() {
        if (!appInteractor.isLoggedIn())
            router.newRootScreen(Screens.AuthFlow)
        else
            router.newRootScreen(Screens.TabsFlow)
    }

    fun onRetry() {
        start()
    }

    fun doNext() {
        router.newRootScreen(Screens.Auth)
    }

    fun onBack() {
        router.exit()
    }
}