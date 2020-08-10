package com.gasolinecalculation.domain.interactors

import com.gasolinecalculation.data.AuthImplRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun getCurrentUser(): FirebaseUser? = repository.getCurrentUser()
}