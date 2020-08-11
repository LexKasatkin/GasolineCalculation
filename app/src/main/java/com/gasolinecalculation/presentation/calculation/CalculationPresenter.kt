package com.gasolinecalculation.presentation.calculation

import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.auth.GetUserTokenUseCase
import com.gasolinecalculation.domain.firestore.GetRefuelsByUserTokenUseCase
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
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
        Timber.e(throwable)
    }

    private fun loadData() {
        launch {
            val userToken = getUserTokenUseCase.getUserToken()
            try {
                userToken?.let {
                    val refuels = getRefuelsByUserTokenUseCase.getRefuelsByUserToken(it)
                    Timber.v(refuels.toString())
                }
            } catch (exception: Exception) {
                proceedCoroutineError(exception)
            }
        }
    }

}
