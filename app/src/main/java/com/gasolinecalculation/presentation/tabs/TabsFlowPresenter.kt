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
//    private val errorHandler: ErrorHandler
) : BasePresenter<TabsFlowView>(dispatchersProvider) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun navigateToSettings() {
        router.navigateTo(Screens.Settings)
    }

    fun navigateToCalculation() {
        router.navigateTo(Screens.Calculation)
    }

    fun onBackPressed() = router.exit()

    override fun proceedCoroutineError(throwable: Throwable) {

    }

    fun toNext() {
        router.startFlow(Screens.AuthFlow)
    }
}
