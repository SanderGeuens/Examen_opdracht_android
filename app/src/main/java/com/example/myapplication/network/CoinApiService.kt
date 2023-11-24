package com.example.myapplication.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    //"https://android-kotlin-fun-mars-server.appspot.com"
    "https://api.coinlore.net/api/tickers"

private val retrofit = Retrofit.Builder().addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).baseUrl(BASE_URL)
    .build()

interface CoinApiService {
    @GET("/")
    suspend fun getCoins():CoinsResponse
}

object CoinApi {
    val retrofitService : CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }
}