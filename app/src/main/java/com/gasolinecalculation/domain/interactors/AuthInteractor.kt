package com.gasolinecalculation.domain.interactors

import com.gasolinecalculation.data.AuthImplRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun signInWithCredential(credential: AuthCredential) =
        repository.googleSignIn(credential)

    suspend fun getCurrentUser(): FirebaseUser? = repository.getCurrentUser()
}