package com.example.myapplication.data

import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.network.CoinApi

interface CoinsRepository {
    suspend fun getCoins():List<ApiCryptoCoin>
}

class NetworkCoinsRepository():CoinsRepository {
    override suspend fun getCoins(): List<ApiCryptoCoin> {
        return CoinApi.retrofitService.getCoins().data
    }

}