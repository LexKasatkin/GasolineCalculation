package com.gasolinecalculation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject


abstract class FlowFragment : MvpAppCompatFragment() {
    open val layoutRes: Int = com.gasolinecalculation.R.layout.layout_flow_container
    open val containerId: Int = com.gasolinecalculation.R.id.flow_container

//    private val currentFragment
//        get() = childFragmentManager.findFragmentById(containerId) as? BackButtonListener

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    protected val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, containerId) {
            override fun activityBack() {
                onExit()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                //fix incorrect order lifecycle callback of MainFlowFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutRes, container, false)

    open fun onExit() {}

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}