package com.gasolinecalculation.presentation.calculation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CalculationView : MvpView {
//
//    fun showProgress(isVisible: Boolean)
//
//    @StateStrategyType(OneExecutionStateStrategy::class)
//    fun showMessage(message: String)
}
