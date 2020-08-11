package com.gasolinecalculation.domain.auth

import com.gasolinecalculation.data.AuthImplRepository
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun getUserToken(): String? = repository.getUserToken()
}