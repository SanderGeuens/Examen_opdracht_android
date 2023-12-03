package com.example.myapplication.network

import com.example.myapplication.model.CryptoCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiCryptoCoin(
    val id: String,
val rank: String,
val symbol: String,
val name: String,
val supply: String,
val maxSupply: String?,
val marketCapUsd: String,
val volumeUsd24Hr: String,
val priceUsd: String,
val changePercent24Hr: String,
val vwap24Hr: String?,
val explorer: String?

    ) {
}

fun Flow<List<ApiCryptoCoin>>.asDomainObjects(): Flow<List<CryptoCoin>> {
    var domainList = this.map {
        it.asDomainObjects()
    }
    return domainList
}

fun List<ApiCryptoCoin>.asDomainObjects(): List<CryptoCoin>{
    var domainList = this.map {
        CryptoCoin(id=it.id,
            rank=it.rank,
            symbol = it.symbol,
            name = it.name,
            supply = it.supply,
            maxSupply = it.maxSupply?:null,
            marketCapUsd = it.marketCapUsd,
            volumeUsd24Hr = it.volumeUsd24Hr,
            priceUsd = it.priceUsd,
            changePercent24Hr = it.changePercent24Hr,
            vwap24Hr =it.vwap24Hr?:null)
    }
    return domainList
}