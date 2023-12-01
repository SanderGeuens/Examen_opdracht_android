package com.example.myapplication.data

import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.network.CoinApiService

interface CoinsRepository {
    suspend fun getCoins():List<ApiCryptoCoin>
}

class NetworkCoinsRepository(private val coinApiService:CoinApiService):CoinsRepository {
    override suspend fun getCoins(): List<ApiCryptoCoin> = coinApiService.getCoins().data

}