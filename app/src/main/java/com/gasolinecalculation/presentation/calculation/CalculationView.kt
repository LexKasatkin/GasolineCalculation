package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.ui.calculation.model.RefuelItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CalculationView : MvpView {

    fun showProgress(isVisible: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    fun showRefuels(refuels: List<RefuelItem>)
}
