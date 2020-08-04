package com.gasolinecalculation.ui.calculation

import android.os.Bundle
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.calculation.CalculationPresenter
import com.gasolinecalculation.presentation.calculation.CalculationView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class CalculationFragment : BaseFragment(), CalculationView {

    override val layoutRes = R.layout.fragment_calculation

    @InjectPresenter
    lateinit var presenter: CalculationPresenter

    @ProvidePresenter
    fun providePresenter(): CalculationPresenter =
        scope.getInstance(CalculationPresenter::class.java)


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