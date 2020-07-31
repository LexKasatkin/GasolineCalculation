package com.gasolinecalculation.ui.main

import android.os.Bundle
import com.gasolinecalculation.Screens
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.presentation.main.AppPresenter
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject


class AppActivity : BaseSingleActivity(), MvpView {
    @InjectPresenter
    lateinit var presenter: AppPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(DI.APP_SCOPE)
            .getInstance(AppPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this@AppActivity, Toothpick.openScope(DI.APP_SCOPE))

        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        if (savedInstanceState == null) {
            presenter.coldStart()
        }
    }
}

class AppPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<MvpView>() {
    fun coldStart() {
        router.newRootScreen(Screens.TopFlow)
    }
}