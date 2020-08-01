package com.gasolinecalculation.di.modules

import com.gasolinecalculation.di.providers.OkHttpProvider
import com.gasolinecalculation.domain.model.AuthData
import com.gasolinecalculation.domain.model.AuthDataPrefs
import okhttp3.OkHttpClient
import toothpick.ktp.binding.module

fun networkModule() = module {
    bind(AuthData::class.java).to(AuthDataPrefs::class.java).singleton()
    bind(OkHttpClient::class.java).toProvider(OkHttpProvider::class.java).providesSingleton()
}