package com.gasolinecalculation.ui.auth

import android.content.Intent
import android.os.Bundle
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.auth.AuthPresenter
import com.gasolinecalculation.presentation.auth.AuthView
import com.gasolinecalculation.util.RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber


class AuthFragment : BaseFragment(), AuthView {
    private lateinit var googleSignInClient: GoogleSignInClient
    private var errorSnackbar: Snackbar? = null

    override val layoutRes = R.layout.fragment_auth

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter =
        scope.getInstance(AuthPresenter::class.java)

    override fun startGoogleSignIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAuthorization()
        btnGoogleSignIn.setOnClickListener { presenter.signIn(currentUser) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun showError(message: String) {
        errorSnackbar?.dismiss()

        view?.let { view ->
            errorSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Retry") {
                    presenter.signIn(currentUser)
                }
                show()
            }
        }
    }

    override fun showProgress() {
        showProgressDialog(true)
    }

    override fun hideProgress() {
        showProgressDialog(false)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    private fun initAuthorization() {
        val googleOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleOptions);
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Timber.v(account?.id)
            account?.idToken?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            Timber.v("signInResult:failed code=${e.statusCode}")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth?.signInWithCredential(credential)?.addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                currentUser = auth?.currentUser
                currentUser?.let { presenter.navigateToTabs() }
                Timber.v("signInWithCredential:success")
            } else {
                Timber.e(task.exception, "signInWithCredential:failure")
                presenter.onGoogleAuthError()
            }
        }
    }
}