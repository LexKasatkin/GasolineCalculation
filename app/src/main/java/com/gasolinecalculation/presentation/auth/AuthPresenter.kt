package com.gasolinecalculation.presentation.auth

import android.content.Context
import com.gasolinecalculation.R
import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.domain.interactors.AuthInteractor
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import com.gasolinecalculation.system.ResourceManager
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject constructor(
    private val context: Context,
    private val resourceManager: ResourceManager,
    private val router: FlowRouter,
    private val interactor: AuthInteractor,
    dispatchersProvider: DispatchersProvider
) : BasePresenter<AuthView>(dispatchersProvider) {

    override fun proceedCoroutineError(throwable: Throwable) {
        viewState.showError(throwable.localizedMessage)
        Timber.e(throwable)
    }

    fun onBackPressed() = router.exit()

    fun googleSignIn() {
        launch {
            if (interactor.getCurrentUser() == null) viewState.startGoogleSignIn()
            else navigateToTabs()
        }
    }

    fun firebaseAuthWithGoogle(userToken: String) {
        launch {
            try {
                val credential = GoogleAuthProvider.getCredential(userToken, null)
                interactor.signInWithCredential(credential)
                navigateToTabs()
            } catch (e: Exception) {
                proceedCoroutineError(e)
            }
        }
//        auth?.signInWithCredential(credential)?.addOnCompleteListener(context) { task ->
//            if (task.isSuccessful) {
//                currentUser = auth?.currentUser
//                currentUser?.let { presenter.navigateToTabs() }
//                Timber.v("signInWithCredential:success")
//            } else {
//                Timber.e(task.exception, "signInWithCredential:failure")
//                presenter.onGoogleAuthError()
//            }
//        }
    }

    fun navigateToTabs() {
        router.newRootFlow(Screens.TabsFlow)
    }

    fun onGoogleAuthError() {
        viewState.showError(resourceManager.getString(R.string.authentication_error))
    }
}
