package com.example.myapplication.fake

import com.example.myapplication.network.CoinApiService
import com.example.myapplication.network.CoinsResponse

class FakeCoinApiService:CoinApiService {
    override suspend fun getCoins(): CoinsResponse {
        return FakeDataSource.coinResponse
    }
}