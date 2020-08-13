package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.R
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.auth.GetUserTokenUseCase
import com.gasolinecalculation.domain.firestore.GetRefuelsByUserTokenUseCase
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import com.gasolinecalculation.system.ResourceManager
import com.gasolinecalculation.ui.calculation.model.RefuelItem
import com.gasolinecalculation.ui.calculation.model.toPresentation
import kotlinx.coroutines.launch
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

/**
 * A presenter class for calculation screen. Manages the logic of loading refuels.
 */
@InjectViewState
class CalculationPresenter @Inject constructor(
    private val getRefuelsByUserTokenUseCase: GetRefuelsByUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val router: FlowRouter,
    private val resourceManager: ResourceManager,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<CalculationView>(dispatchersProvider) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onFirstLoading()
    }

    override fun proceedCoroutineError(throwable: Throwable) {
        throwable.localizedMessage?.let { viewState.showMessage(it) }
        Timber.e(throwable)
    }

    /**
     * On back pressed handler.
     */
    fun onBackPressed() = router.exit()

    /**
     * Handle of refresh list of refuels.
     */
    fun onRefresh() {
        launch {
            viewState.showRefreshView(true)
            loadData()
            viewState.showRefreshView(false)
        }
    }

    /**
     * First loading logic.
     */
    private fun onFirstLoading() {
        launch {
            viewState.showProgress(true)
            loadData()
            viewState.showProgress(false)
        }
    }

    /**
     * Load common data - list of refuels.
     */
    private fun loadData() {
        launch {
            val userToken = getUserTokenUseCase.getUserToken()
            try {
                userToken?.let { token ->
                    val refuelItems = getRefuelsByUserTokenUseCase.getRefuelsByUserToken(token)
                        .map {
                            RefuelItem(
                                it.toPresentation(
                                    resourceManager.getString(R.string.gas_cost_format),
                                    resourceManager.getString(R.string.mileage_format),
                                    resourceManager.getString(R.string.date_time_pattern),
                                    resourceManager.getString(R.string.currency)
                                )
                            )
                        }
                    viewState.showRefuels(refuelItems)
                }
            } catch (exception: Exception) {
                proceedCoroutineError(exception)
            }
        }
    }
}
