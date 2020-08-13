package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.ui.calculation.model.RefuelItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Calculation view for showing calculation UI.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface CalculationView : MvpView {

    /**
     * Show progress if [isVisible] true, hide - otherwise.
     */
    fun showProgress(isVisible: Boolean)

    /**
     * Show [message].
     */
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    /**
     * Show list of refuels.
     */
    fun showRefuels(refuels: List<RefuelItem>)

    /**
     * Show refresh view if [refresh], hide - otherwise.
     */
    fun showRefreshView(refresh: Boolean)
}
