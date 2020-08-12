package com.gasolinecalculation.domain.firestore

import com.gasolinecalculation.domain.model.RefuelDomain
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetRefuelsByUserTokenUseCase @Inject constructor(
    private val repository: FirestoreRepository,
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getRefuelsByUserToken(userToken: String): List<RefuelDomain> {
        val query = firebaseFirestore.collection("refuels")
            .whereEqualTo("userToken", userToken)
            .orderBy("dateTime")
            .orderBy("mileage")

        return repository.getQueryCollection(query)
            .map { it.toRefuel() }
    }
}

/**
 * Converts [DocumentSnapshot] to [RefuelDomain] instance.
 */
fun DocumentSnapshot.toRefuel(): RefuelDomain {
    val cost = this["cost", Float::class.java] ?: 0F
    val mileage = this["mileage", Int::class.java] ?: 0
    val dateTimeCreationSeconds = this.getTimestamp("dateTime", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)?.seconds
    val dateTime = if (dateTimeCreationSeconds == null) DATE_TIME else DateTime(TimeUnit.SECONDS.toMillis(dateTimeCreationSeconds))

    return RefuelDomain(
        cost = cost,
        mileage = mileage,
        dateTime = dateTime
    )
}

val DATE_TIME: DateTime = DateTime(1970, 1, 1, 0, 0, 0)


