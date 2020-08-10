package com.gasolinecalculation.domain.interactors

import com.gasolinecalculation.data.AuthImplRepository
import javax.inject.Inject

class TabsFlowInteractor @Inject constructor(
    private val repository: AuthImplRepository
) {
    suspend fun googleSignOut() = repository.googleSignOut()
}