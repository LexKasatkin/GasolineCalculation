package com.gasolinecalculation.domain.firestore

import com.gasolinecalculation.data.model.Refuel
import javax.inject.Inject

class GetRefuelsUseCase @Inject constructor(
    private val repository: FirestoreRepository
) {
    suspend fun getRefuels() = repository.getDocumentCollection("refuels")
        ?.map {
            it.toObject(Refuel::class.java)
        }
}