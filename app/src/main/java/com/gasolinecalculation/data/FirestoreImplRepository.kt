package com.gasolinecalculation.data

import com.gasolinecalculation.domain.firestore.FirestoreRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreImplRepository @Inject constructor(val db: FirebaseFirestore) : FirestoreRepository {
    override suspend fun getDocumentCollection(document: String): List<DocumentSnapshot>? =
        db.collection(document)
            .get()
            .await()
            .documents

    override suspend fun getQueryCollection(query: Query): List<DocumentSnapshot> =
        query.get()
            .await()
            .documents
}
