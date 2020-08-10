package com.gasolinecalculation.data

import com.gasolinecalculation.util.awaitTaskCompletable
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthImplRepository @Inject constructor(
    private val auth: FirebaseAuth?
) : AuthRepository {
    override suspend fun googleSignOut(): Unit? = auth?.signOut()

    override suspend fun googleSignIn(credential: AuthCredential) =
        awaitTaskCompletable(auth?.signInWithCredential(credential))


    override suspend fun getCurrentUser() = auth?.currentUser
}

