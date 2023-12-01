package com.example.myapplication.fake

import com.example.myapplication.data.CoinsRepository
import com.example.myapplication.network.ApiCryptoCoin

class FakeCoinsRepository:CoinsRepository {
    override suspend fun getCoins(): List<ApiCryptoCoin> {
        return FakeDataSource.coinList
    }
}