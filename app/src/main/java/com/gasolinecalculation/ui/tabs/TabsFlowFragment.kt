package com.gasolinecalculation.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gasolinecalculation.R
import com.gasolinecalculation.Screens
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.di.modules.NavigationModule
import com.gasolinecalculation.util.setLaunchScreen
import kotlinx.android.synthetic.main.tabs_flow_fragment.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class TabsFlowFragment : BaseFragment() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override val layoutRes = R.layout.tabs_flow_fragment

//    private val currentFragment
//        get() = childFragmentManager.findFragmentById(R.id.mainContainer) as? BaseFragment


    override fun installModules(scope: Scope) {
        scope.installModules(
            NavigationModule(scope.getInstance(Router::class.java))
        )
    }

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this.activity!!, childFragmentManager, R.id.bottomNavigation) {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, scope)

        if (childFragmentManager.fragments.isEmpty()) {
//            childFragmentManager
//                .beginTransaction()
//                .replace(R.id.navDrawerContainer, NavigationDrawerFragment())
//                .commitNow()

            navigator.setLaunchScreen(Screens.CalculationFlow)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.calculation -> {
                    router.navigateTo(Screens.CalculationFlow)
                    true
                }
                R.id.settings -> {
                    router.navigateTo(Screens.SettingsFlow)
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

        super.onPause()
    }

    //region nav drawer
//    private fun openNavDrawer(open: Boolean) {
//        if (open) drawerLayout.openDrawer(GravityCompat.START)
//        else drawerLayout.closeDrawer(GravityCompat.START)
//    }
//
//    private fun updateNavDrawer() {
//        childFragmentManager.executePendingTransactions()
//
//        drawerFragment?.let { drawerFragment ->
//            currentFragment?.let {
//                when (it) {
////                    is MainFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.ACTIVITY)
////                    is ProjectsContainerFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.PROJECTS)
////                    is AboutFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.ABOUT)
//                }
//            }
//        }
//    }
    //endregion
}