package com.gasolinecalculation

import androidx.annotation.IdRes
import com.gasolinecalculation.ui.auth.AuthFlowFragment
import com.gasolinecalculation.ui.auth.AuthFragment
import com.gasolinecalculation.ui.calculation.CalculationFlowFragment
import com.gasolinecalculation.ui.calculation.CalculationFragment
import com.gasolinecalculation.ui.settings.SettingsFlowFragment
import com.gasolinecalculation.ui.settings.SettingsFragment
import com.gasolinecalculation.ui.splash.SplashFragment
import com.gasolinecalculation.ui.tabs.TabsFlowFragment
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

    object SettingsFlow : SupportAppScreen() {
        override fun getFragment() = SettingsFlowFragment()
    }

    object CalculationFlow : SupportAppScreen() {
        override fun getFragment() = CalculationFlowFragment()
    }

    object Calculation : SupportAppScreen() {
        override fun getFragment() = CalculationFragment()
    }

    object Settings : SupportAppScreen() {
        override fun getFragment() = SettingsFragment()
    }

    object Auth : SupportAppScreen() {
        override fun getFragment() = AuthFragment()
    }

    object Splash : SupportAppScreen() {
        override fun getFragment() = SplashFragment()
    }
}