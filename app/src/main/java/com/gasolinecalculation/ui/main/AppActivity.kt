package com.gasolinecalculation.ui.main

import android.os.Bundle
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.presentation.main.AppPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick


class AppActivity : BaseSingleActivity() {
    @InjectPresenter
    lateinit var presenter: AppPresenter

    @ProvidePresenter
    fun providePresenter(): AppPresenter =
        Toothpick.openScope(DI.APP_SCOPE)
            .getInstance(AppPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))

        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        if (savedInstanceState == null) {
            presenter.coldStart()
        }
    }
}