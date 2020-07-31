package com.gasolinecalculation.ui.main

import android.os.Bundle
import android.view.View
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.presentation.main.SplashPresenter
import com.gasolinecalculation.presentation.main.SplashView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_splash.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class SplashFragment : BaseFragment(), SplashView {
    override val layoutRes = R.layout.fragment_splash
    private var errorSnackbar: Snackbar? = null

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter = Toothpick.openScope(DI.TOP_FLOW_SCOPE).getInstance(
        SplashPresenter::class.java
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        errorSnackbar?.dismiss()
    }

    override fun showError(msg: String) {
        progress.visibility = View.INVISIBLE
        errorSnackbar?.dismiss()

        view?.let { view ->
            errorSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Retry") { presenter.onRetry() }
                show()
            }
        }
    }
}