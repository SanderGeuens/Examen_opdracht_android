package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.model.CryptoCoin

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

fun DbCryptoCoin.asDomainCoin(): CryptoCoin {
    return CryptoCoin(
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
fun List<DbCryptoCoin>.asDomainCoins(): List<CryptoCoin>{
    var taskList = this.map {
        CryptoCoin(
            id=it.id,
            rank=it.rank,
            symbol = it.symbol,
            name = it.name,
            supply = it.supply,
            maxSupply = it.maxSupply?:null,
            marketCapUsd = it.marketCapUsd,
            volumeUsd24Hr = it.volumeUsd24Hr,
            priceUsd = it.priceUsd,
            changePercent24Hr = it.changePercent24Hr,
            vwap24Hr =it.vwap24Hr?:null
        )
    }
    return taskList
}