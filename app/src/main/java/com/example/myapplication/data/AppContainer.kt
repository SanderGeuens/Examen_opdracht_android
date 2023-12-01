package com.example.myapplication.data

import com.example.myapplication.network.CoinApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val coinsRepository: CoinsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =
    //"https://android-kotlin-fun-mars-server.appspot.com"
        //"https://api.coinlore.net/api/tickers/"
        "https://api.coincap.io/v2/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            /*ScalarsConverterFactory.create()*/
            Json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()


    private val retrofitService : CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }

    override val coinsRepository: CoinsRepository by lazy {
        NetworkCoinsRepository(retrofitService)
    }
}