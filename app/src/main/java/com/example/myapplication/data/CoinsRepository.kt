package com.example.myapplication.data

import com.example.myapplication.model.CryptoCoin
import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.network.CoinApiService
import com.example.myapplication.network.asDomainObjects

interface CoinsRepository {
    suspend fun getCoins():List<CryptoCoin>
}

class NetworkCoinsRepository(private val coinApiService:CoinApiService):CoinsRepository {
    override suspend fun getCoins(): List<CryptoCoin> = coinApiService.getCoins().data.asDomainObjects()

}