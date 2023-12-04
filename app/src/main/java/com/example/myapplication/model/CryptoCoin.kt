package com.example.myapplication.model

import com.example.myapplication.data.database.DbCryptoCoin
import com.example.myapplication.data.database.asDomainCoin

data class CryptoCoin(
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
) {
}

fun CryptoCoin.asDbCoin(): DbCryptoCoin {
    return DbCryptoCoin(
        id=this.id,
        rank=this.rank,
        symbol = this.symbol,
        name = this.name,
        supply = this.supply,
        maxSupply = this.maxSupply?:null,
        marketCapUsd = this.marketCapUsd,
        volumeUsd24Hr = this.volumeUsd24Hr,
        priceUsd = this.priceUsd,
        changePercent24Hr = this.changePercent24Hr,
        vwap24Hr =this.vwap24Hr?:null,
    )
}