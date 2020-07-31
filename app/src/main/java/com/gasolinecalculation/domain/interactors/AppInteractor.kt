package com.gasolinecalculation.domain.interactors

import com.gasolinecalculation.domain.model.AuthData
import javax.inject.Inject


class AppInteractor @Inject constructor(
    private val authData: AuthData
) {
    fun requestSmsCode(phone: String)
//            : Completable
    {
        authData.phone = phone
//        return Completable.complete()
//            .delaySubscription(1, TimeUnit.SECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
    }

    fun login(smsCode: String)
//            : Completable
    {
//        authData.clientId = "clientId"
//        authData.accessToken = "accessToken"
//        authData.refreshToken = "refreshToken"
//        return Completable.complete()
//            .delaySubscription(1, TimeUnit.SECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
    }

    fun logout() {
        authData.clear()
    }

    fun isLoggedIn(): Boolean {
        return authData.phone != null &&
                authData.clientId != null &&
                authData.accessToken != null &&
                authData.refreshToken != null
    }


}