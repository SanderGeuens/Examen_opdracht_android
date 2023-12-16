package com.example.myapplication.data

import com.example.myapplication.data.database.CoinDb
import com.example.myapplication.network.CoinApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import android.content.Context
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val coinsRepository: CoinsRepository
}

class DefaultAppContainer (private val context: Context): AppContainer {
    private val baseUrl =
        "https://api.coincap.io/v2/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()


    private val retrofitService : CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }

    override val coinsRepository: CoinsRepository by lazy {
        CashingCoinsRepository( coinDao = CoinDb.getDatabase(context = context).coinDao(), coinApiService = retrofitService)
    }
}