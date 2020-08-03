package com.gasolinecalculation.ui

import android.os.Bundle
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.presentation.AppPresenter
import com.gasolinecalculation.ui.global.BaseSingleActivity
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
        presenter.onLaunch()

        if (savedInstanceState == null) {
            presenter.coldStart()
        }
    }
}