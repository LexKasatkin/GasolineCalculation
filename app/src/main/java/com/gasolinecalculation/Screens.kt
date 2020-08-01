package com.gasolinecalculation

import androidx.annotation.IdRes
import com.gasolinecalculation.ui.AuthFragment
import com.gasolinecalculation.ui.main.SplashFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

open class SupportAppTabScreen(@IdRes val navigationIdRes: Int) : SupportAppScreen()

object Screens {

    // Flows
    object Auth : SupportAppScreen() {
        override fun getFragment() = AuthFragment()
    }


    object Splash : SupportAppScreen() {
        override fun getFragment() = SplashFragment()
    }

}