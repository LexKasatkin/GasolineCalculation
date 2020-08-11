package com.gasolinecalculation.presentation.tabs

import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.auth.GoogleSignOutUseCase
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class TabsFlowPresenter @Inject constructor(
    private val googleSignOutUseCase: GoogleSignOutUseCase,
    private val router: FlowRouter,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<TabsFlowView>(dispatchersProvider) {

    override fun proceedCoroutineError(throwable: Throwable) {
        Timber.e(throwable)
    }

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

    fun onSignOut(task: Task<Void>) {
        launch {
            try {
                if (task.isSuccessful) {
                    googleSignOutUseCase.googleSignOut()
                    navigateToAuth()
                }
            } catch (e: Exception) {
                proceedCoroutineError(e)
            }
        }
    }

    private fun navigateToAuth() {
        router.startFlow(Screens.AuthFlow)
    }
}
