package com.gasolinecalculation.ui.auth

import android.content.Intent
import android.os.Bundle
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.auth.AuthPresenter
import com.gasolinecalculation.presentation.auth.AuthView
import com.gasolinecalculation.util.RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
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
        initGoogleAuthorization()
        btnGoogleSignIn.setOnClickListener { presenter.googleSignIn() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            handleGoogleSignInResult(data)
        }
    }

    override fun showError(message: String) {
        errorSnackbar?.dismiss()

        view?.let { view ->
            errorSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Retry") {
                    presenter.googleSignIn()
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

    private fun initGoogleAuthorization() {
        val googleOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleOptions);
    }

    fun handleGoogleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        var userToken: String? = null
        try {
            val account = task?.getResult(ApiException::class.java)
            userToken = account?.idToken
            Timber.v(userToken)
        } catch (e: ApiException) {
            Timber.v("signInResult:failed code=${e.statusCode}")
        }
        userToken?.let { presenter.firebaseAuthWithGoogle(it) }
    }
}