package com.gasolinecalculation.ui.settings

import android.os.Bundle
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.settings.SettingsPresenter
import com.gasolinecalculation.presentation.settings.SettingsView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SettingsFragment : BaseFragment(), SettingsView {

    override val layoutRes = R.layout.fragment_settings

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter =
        scope.getInstance(SettingsPresenter::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
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
    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}