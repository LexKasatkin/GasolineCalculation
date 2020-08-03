package com.gasolinecalculation.ui.tabs

import android.os.Bundle
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.tabs.TabsPresenter
import com.gasolinecalculation.presentation.tabs.TabsView
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class TabsFragment : BaseFragment(), TabsView {

    override val layoutRes = R.layout.fragment_tabs

    @InjectPresenter
    lateinit var presenter: TabsPresenter

    @ProvidePresenter
    fun providePresenter(): TabsPresenter =
        scope.getInstance(TabsPresenter::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnLogin.setOnClickListener { presenter.toNext() }
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