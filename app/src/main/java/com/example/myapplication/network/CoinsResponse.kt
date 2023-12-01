package com.example.myapplication.network

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponse (
    val data:List<ApiCryptoCoin>,
    val timestamp: Long
) {

}