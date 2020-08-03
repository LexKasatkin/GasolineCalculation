package com.gasolinecalculation

import androidx.annotation.IdRes
import com.gasolinecalculation.ui.auth.AuthFlowFragment
import com.gasolinecalculation.ui.auth.AuthFragment
import com.gasolinecalculation.ui.splash.SplashFragment
import com.gasolinecalculation.ui.tabs.TabsFlowFragment
import com.gasolinecalculation.ui.tabs.TabsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

open class SupportAppTabScreen(@IdRes val navigationIdRes: Int) : SupportAppScreen()

object Screens {

    // Flows
    object AuthFlow : SupportAppScreen() {
        override fun getFragment() = AuthFlowFragment()
    }

    object TabsFlow : SupportAppScreen() {
        override fun getFragment() = TabsFlowFragment()
    }

    object Tabs : SupportAppScreen() {
        override fun getFragment() = TabsFragment()
    }

    object Auth : SupportAppScreen() {
        override fun getFragment() = AuthFragment()
    }

    object Splash : SupportAppScreen() {
        override fun getFragment() = SplashFragment()
    }

}