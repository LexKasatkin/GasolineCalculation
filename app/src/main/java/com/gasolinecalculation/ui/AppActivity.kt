package com.gasolinecalculation.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.gasolinecalculation.R
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
        setTheme(R.style.AppTheme)

        window.apply {
            statusBarColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
            navigationBarColor = ContextCompat.getColor(context, R.color.colorPrimary)
        }

        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))

        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        presenter.onLaunch()

        if (savedInstanceState == null) {
            presenter.coldStart()
        }
    }
}