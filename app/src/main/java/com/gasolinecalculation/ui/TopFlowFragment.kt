package com.gasolinecalculation.ui

import android.os.Bundle
import com.gasolinecalculation.Screens
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.di.flowModule
import com.gasolinecalculation.system.initDynamicUiScope
import com.gasolinecalculation.ui.main.FlowFragment
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject


class TopFlowFragment : FlowFragment(), MvpView {

    private val scopeName: String by initDynamicUiScope { realScopeName ->
        DI.TOP_FLOW_SCOPE = realScopeName // Save the dynamic scope name
        val scope = Toothpick.openScopes(DI.SERVER_SCOPE, DI.TOP_FLOW_SCOPE)
        scope.installModules(
            flowModule(scope.getInstance(Router::class.java))
        )
    }

    @InjectPresenter
    lateinit var presenter: TopFlowPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(scopeName)
            .getInstance(TopFlowPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(scopeName))

        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty())
            navigator.setLaunchScreen(Screens.Splash)
    }

    override fun onExit() {
        presenter.onExit()
    }

}


class TopFlowPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<MvpView>() {
    fun onExit() {
        router.exit()
    }
}