package com.gasolinecalculation.presentation.auth

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthView : MvpView {

    /**
     * Sign In to the application.
     */
    fun startGoogleSignIn()

    /**
     * Show error [message].
     */
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

    /**
     * Show progress dialog
     */
    fun showProgress()

    /**
     * Hide progress dialog
     */
    fun hideProgress()
}
