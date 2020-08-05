package com.gasolinecalculation.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gasolinecalculation.R
import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.di.modules.NavigationModule
import com.gasolinecalculation.presentation.tabs.TabsFlowPresenter
import com.gasolinecalculation.presentation.tabs.TabsFlowView
import com.gasolinecalculation.util.setLaunchScreen
import kotlinx.android.synthetic.main.fragment_tabs.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class TabsFlowFragment : BaseFragment(), TabsFlowView {
    override val layoutRes: Int = R.layout.fragment_tabs

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.flow_container) as? BaseFragment

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: TabsFlowPresenter

    @ProvidePresenter
    fun providePresenter(): TabsFlowPresenter =
        scope.getInstance(TabsFlowPresenter::class.java)

    override fun installModules(scope: Scope) {
        scope.installModules(
            NavigationModule(scope.getInstance(Router::class.java))
        )
    }

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.flow_container) {
            override fun activityBack() {
                router.exit()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.calculation -> {
                    presenter.navigateToCalculation()
                    true
                }
                R.id.settings -> {
                    presenter.navigateToSettings()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, scope)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(Screens.CalculationFlow)
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}