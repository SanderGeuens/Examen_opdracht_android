package com.example.myapplication.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    //"https://android-kotlin-fun-mars-server.appspot.com"
    //"https://api.coinlore.net/api/tickers/"
    "https://api.coincap.io/v2/"

private val logger = HttpLoggingInterceptor().apply{level = HttpLoggingInterceptor.Level.BASIC}

private val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        /*ScalarsConverterFactory.create()*/
    Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface CoinApiService {
    @GET("assets")
    suspend fun getCoins():CoinsResponse
}

object CoinApi {
    val retrofitService : CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }
}