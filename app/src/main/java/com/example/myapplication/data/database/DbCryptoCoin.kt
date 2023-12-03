package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="coins")
data class DbCryptoCoin (
    @PrimaryKey
    val id: String = "",
    val rank: String = "",
    val symbol: String = "",
    val name: String = "",
    val supply: String = "",
    val maxSupply: String? ="",
    val marketCapUsd: String ="",
    val volumeUsd24Hr: String ="",
    val priceUsd: String ="",
    val changePercent24Hr: String ="",
    val vwap24Hr: String? ="",
){
}