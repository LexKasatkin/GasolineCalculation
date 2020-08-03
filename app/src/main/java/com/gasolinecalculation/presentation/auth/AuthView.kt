package com.gasolinecalculation.presentation.auth

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthView : MvpView {
//
//    fun showProgress(isVisible: Boolean)
//
//    @StateStrategyType(OneExecutionStateStrategy::class)
//    fun showMessage(message: String)
}
