package com.gasolinecalculation.di.modules

import com.gasolinecalculation.data.AuthImplRepository
import com.gasolinecalculation.data.FirestoreImplRepository
import com.gasolinecalculation.di.providers.OkHttpProvider
import com.gasolinecalculation.domain.auth.AuthRepository
import com.gasolinecalculation.domain.firestore.FirestoreRepository
import com.gasolinecalculation.domain.model.AuthData
import com.gasolinecalculation.domain.model.AuthDataPrefs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import toothpick.ktp.binding.module

fun ServerModule() = module {
    bind(AuthData::class.java).to(AuthDataPrefs::class.java).singleton()
    bind(OkHttpClient::class.java).toProvider(OkHttpProvider::class.java).providesSingleton()

    // Firestore
    bind(FirebaseAuth::class.java).toInstance(Firebase.auth)
    val db = FirebaseFirestore.getInstance()
    bind(FirebaseFirestore::class.java).toInstance(db)

    // Repository
    bind(AuthRepository::class.java).to(AuthImplRepository::class.java).singleton()
    bind(FirestoreRepository::class.java).to(FirestoreImplRepository::class.java).singleton()
}