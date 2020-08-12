package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.auth.GetUserTokenUseCase
import com.gasolinecalculation.domain.firestore.GetRefuelsByUserTokenUseCase
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import com.gasolinecalculation.ui.calculation.model.RefuelItem
import com.gasolinecalculation.ui.calculation.model.toPresentation
import kotlinx.coroutines.launch
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject


@InjectViewState
class CalculationPresenter @Inject constructor(
    private val getRefuelsByUserTokenUseCase: GetRefuelsByUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val router: FlowRouter,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<CalculationView>(dispatchersProvider) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun onBackPressed() = router.exit()

    override fun proceedCoroutineError(throwable: Throwable) {
        throwable.localizedMessage?.let { viewState.showMessage(it) }
        Timber.e(throwable)
    }

    private fun loadData() {
        launch {
            viewState.showProgress(true)
            val userToken = getUserTokenUseCase.getUserToken()
            try {
                userToken?.let { token ->
                    val refuelItems = getRefuelsByUserTokenUseCase.getRefuelsByUserToken(token)
                        .map { RefuelItem(it.toPresentation()) }
                    viewState.showRefuels(refuelItems)
                }
            } catch (exception: Exception) {
                proceedCoroutineError(exception)
            }
            viewState.showProgress(false)
        }
    }

}
