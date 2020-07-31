package com.gasolinecalculation.ui

import android.os.Bundle
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.AuthPresenter
import com.gasolinecalculation.presentation.AuthView
import moxy.presenter.InjectPresenter

class AuthFragment : BaseFragment(), AuthView {

    override val layoutRes = R.layout.fragment_auth

    @InjectPresenter
    lateinit var presenter: AuthPresenter

//    @ProvidePresenter
//    fun providePresenter(): AuthPresenter =
//        scope.getInstance(AuthPresenter::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        view?.addSystemBottomPadding()
//        toolbar.apply {
//            setNavigationOnClickListener { presenter.onBackPressed() }
//            addSystemTopPadding()
//        }
//        btnLogin.setOnClickListener {
//            presenter.login(
//                etEmail.text.toString(),
//                etPassword.text.toString()
//            )
//        }

//    }

//    override fun showProgress(isVisible: Boolean) {
//        showProgressDialog(isVisible)
//    }
//
//    override fun showMessage(message: String) {
//        showSnackMessage(message)
//    }
//
//    override fun onBackPressed() {
//        presenter.onBackPressed()
//    }
    }
}