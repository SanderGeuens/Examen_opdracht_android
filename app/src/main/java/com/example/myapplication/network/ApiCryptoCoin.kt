package com.example.myapplication.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiCryptoCoin(
    val id:String,
    val symbol:String,
    val name:String,
    val nameid:String,
    val rank:Int,
    val price_usd:Double,
    val percent_change_24h: Double,
    val percent_change_1h: Double,
    val percent_change_7d: Double,
    val price_btc: Double,
    val market_cap_usd: Double,
    val volume24: Double,
    val volume24a: Double,
    val csupply: Double,
    val tsupply: Int,
    val msupply: Int

    ) {
}