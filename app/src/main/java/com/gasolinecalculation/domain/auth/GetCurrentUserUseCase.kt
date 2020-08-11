package com.gasolinecalculation.domain.auth

import com.gasolinecalculation.data.AuthImplRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun getCurrentUser(): FirebaseUser? = repository.getCurrentUser()
}