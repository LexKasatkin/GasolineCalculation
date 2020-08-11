package com.gasolinecalculation.presentation.auth

import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.auth.GetCurrentUserUseCase
import com.gasolinecalculation.domain.auth.GoogleSignInUseCase
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject constructor(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val router: FlowRouter,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<AuthView>(dispatchersProvider) {

    override fun proceedCoroutineError(throwable: Throwable) {
        throwable.localizedMessage?.let { viewState.showError(it) }
        Timber.e(throwable)
    }

    fun onBackPressed() = router.exit()

    fun googleSignIn() {
        launch {
            if (getCurrentUserUseCase.getCurrentUser() == null) viewState.startGoogleSignIn()
            else navigateToTabs()
        }
    }

    fun firebaseAuthWithGoogle(userToken: String) {
        launch {
            try {
                val credential = GoogleAuthProvider.getCredential(userToken, null)
                googleSignInUseCase.signInWithCredential(credential)
                navigateToTabs()
            } catch (e: Exception) {
                proceedCoroutineError(e)
            }
        }
    }

    private fun navigateToTabs() {
        router.newRootFlow(Screens.TabsFlow)
    }
}
