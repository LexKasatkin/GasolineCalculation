package com.gasolinecalculation.domain.firestore

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

interface FirestoreRepository {
    suspend fun getDocumentCollection(document: String): List<DocumentSnapshot>?

    suspend fun getQueryCollection(query: Query): List<DocumentSnapshot>
}