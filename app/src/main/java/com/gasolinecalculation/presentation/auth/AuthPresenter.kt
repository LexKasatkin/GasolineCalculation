package com.gasolinecalculation.presentation.auth

import com.gasolinecalculation.R
import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import com.gasolinecalculation.system.ResourceManager
import com.google.firebase.auth.FirebaseUser
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject constructor(
    private val resourceManager: ResourceManager,
    private val router: FlowRouter,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<AuthView>(dispatchersProvider) {

    override fun proceedCoroutineError(throwable: Throwable) {}

    fun onBackPressed() = router.exit()

    fun googleSignIn(user: FirebaseUser?) {
        if (user == null) viewState.startGoogleSignIn()
        else navigateToTabs()
    }

    fun navigateToTabs() {
        router.newRootFlow(Screens.TabsFlow)
    }

    fun onGoogleAuthError() {
        viewState.showError(resourceManager.getString(R.string.authentication_error))
    }
}
