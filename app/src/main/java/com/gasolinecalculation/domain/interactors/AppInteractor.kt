package com.gasolinecalculation.domain.interactors

import com.gasolinecalculation.common.Prefs
import java.lang.System.currentTimeMillis
import javax.inject.Inject


class AppInteractor @Inject constructor(
    private val prefs: Prefs
) {
    val isFirstLaunch: Boolean
        get() {
            val timeStamp = prefs.firstLaunchTimeStamp
            if (timeStamp == null) {
                prefs.firstLaunchTimeStamp = currentTimeMillis()
                return true
            } else {
                return false
            }
        }

    val hasAccount: Boolean
        get() = prefs.selectedAccount != null

    fun signInToLastSession() {
//        if (!sessionSwitcher.hasSession) {
//            sessionSwitcher.initSession(prefs.getCurrentUserAccount())
//        }
    }
}