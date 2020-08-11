package com.gasolinecalculation.presentation.splash

import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.auth.GetCurrentUserUseCase
import com.gasolinecalculation.system.DispatchersProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class SplashPresenter @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val router: Router,
    dispatchers: DispatchersProvider
) : BasePresenter<SplashView>(dispatchers) {

    fun checkAuthorization() {
        launch {
            viewState.showProgress()
            delay(1000)
            if (getCurrentUserUseCase.getCurrentUser() == null)
                router.newRootScreen(Screens.AuthFlow)
            else
                router.newRootScreen(Screens.TabsFlow)
            viewState.hideProgress()
        }
    }

    fun onRetry() {
        checkAuthorization()
    }

    fun onBack() {
        router.exit()
    }

    override fun proceedCoroutineError(throwable: Throwable) {

    }
}