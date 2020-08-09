package com.gasolinecalculation.presentation.splash

import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.system.DispatchersProvider
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class SplashPresenter @Inject constructor(
    private val router: Router,
    dispatchers: DispatchersProvider
) : BasePresenter<SplashView>(dispatchers) {

    fun checkAuthorization(user: FirebaseUser?) {
        launch {
            viewState.showProgress()
            delay(1000)
            if (user == null)
                router.newRootScreen(Screens.AuthFlow)
            else
                router.newRootScreen(Screens.TabsFlow)
            viewState.hideProgress()
        }
    }

    fun onRetry(user: FirebaseUser?) {
        checkAuthorization(user)
    }

    fun onBack() {
        router.exit()
    }

    override fun proceedCoroutineError(throwable: Throwable) {

    }
}