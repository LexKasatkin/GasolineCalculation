package com.gasolinecalculation.domain.firestore

import com.gasolinecalculation.data.model.Refuel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GetRefuelsByUserTokenUseCase @Inject constructor(
    private val repository: FirestoreRepository,
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getRefuelsByUserToken(userToken: String) {
        val query = firebaseFirestore.collection("refuels")
            .whereArrayContains("userToken", userToken)

        repository.getQueryCollection(query)
            .map { it.toObject(Refuel::class.java) }
    }
}