package com.gasolinecalculation.domain.auth

import com.gasolinecalculation.data.AuthImplRepository
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun signInWithCredential(credential: AuthCredential) =
        repository.googleSignIn(credential)
}