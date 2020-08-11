package com.gasolinecalculation.domain.auth

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun googleSignOut(): Unit?

    suspend fun googleSignIn(credential: AuthCredential)

    suspend fun getCurrentUser(): FirebaseUser?
}