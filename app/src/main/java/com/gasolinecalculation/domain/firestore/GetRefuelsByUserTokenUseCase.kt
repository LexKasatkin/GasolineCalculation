package com.gasolinecalculation.domain.firestore

import com.gasolinecalculation.data.model.Refuel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GetRefuelsByUserTokenUseCase @Inject constructor(
    private val repository: FirestoreRepository,
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getRefuelsByUserToken(userToken: String): List<Refuel> {
        val query = firebaseFirestore.collection("refuels")
            .whereEqualTo("userToken", userToken)
            .orderBy("dateTime")
            .orderBy("mileage")

        return repository.getQueryCollection(query)
            .map { it.toObject(Refuel::class.java) }
            .filterNotNull()
    }
}