package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class CalculationPresenter @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val router: FlowRouter
//    private val errorHandler: ErrorHandler
) : BasePresenter<CalculationView>(dispatchersProvider) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onBackPressed() = router.exit()

    override fun proceedCoroutineError(throwable: Throwable) {

    }

//    override fun proceedCoroutineError(throwable: Throwable) {
//        errorHandler.proceedError(throwable)
//    }
}
