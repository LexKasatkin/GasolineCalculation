package com.gasolinecalculation.presentation.splash

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface SplashView : MvpView {
    fun showProgress()

    fun hideProgress()

    fun showError(message: String)
}
