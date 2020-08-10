package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.interactors.CalculationInteractor
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class CalculationPresenter @Inject constructor(
    private val interactor: CalculationInteractor,
    private val router: FlowRouter,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<CalculationView>(dispatchersProvider) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        interactor.loadData()
    }

    fun onBackPressed() = router.exit()

    override fun proceedCoroutineError(throwable: Throwable) {}
}
