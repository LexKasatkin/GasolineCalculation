package com.gasolinecalculation.presentation.settings

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SettingsView : MvpView {
//
//    fun showProgress(isVisible: Boolean)
//
//    @StateStrategyType(OneExecutionStateStrategy::class)
//    fun showMessage(message: String)
}
