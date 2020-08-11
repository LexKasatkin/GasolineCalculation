package com.gasolinecalculation.domain.auth

import com.gasolinecalculation.data.AuthImplRepository
import javax.inject.Inject

class GoogleSignOutUseCase @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun googleSignOut() = repository.googleSignOut()
}