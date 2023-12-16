package com.example.myapplication.fake

import com.example.myapplication.data.CoinsRepository
import com.example.myapplication.model.CryptoCoin
import com.example.myapplication.network.ApiCryptoCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList

class FakeCoinsRepository:CoinsRepository {
    override fun getCoins(): Flow<List<CryptoCoin>> {
        return FakeDataSource.coinList
    }

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }

    override suspend fun insertCoin(coin: CryptoCoin) {
        TODO("Not yet implemented")
    }
}