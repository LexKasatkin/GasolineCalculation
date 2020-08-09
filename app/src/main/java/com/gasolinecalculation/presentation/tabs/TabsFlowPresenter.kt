package com.gasolinecalculation.presentation.tabs

import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class TabsFlowPresenter @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val router: FlowRouter
) : BasePresenter<TabsFlowView>(dispatchersProvider) {

    fun navigateToSettings() {
        router.navigateTo(Screens.Settings)
    }

    fun navigateToCalculation() {
        router.navigateTo(Screens.Calculation)
    }

    fun onBackPressed() = router.exit()


    fun signOut() {
        viewState.signOut()
    }

    override fun proceedCoroutineError(throwable: Throwable) {
    }

    fun navigateToAuth() {
        router.startFlow(Screens.AuthFlow)
    }
}
