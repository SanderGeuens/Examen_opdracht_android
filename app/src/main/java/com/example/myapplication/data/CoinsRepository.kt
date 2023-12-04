package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.data.database.CoinDao
import com.example.myapplication.data.database.asDomainCoins
import com.example.myapplication.model.CryptoCoin
import com.example.myapplication.model.asDbCoin
import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.network.CoinApiService
import com.example.myapplication.network.asDomainObjects
import com.example.myapplication.network.getCoinsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface CoinsRepository {
    suspend fun getCoins(): Flow<List<CryptoCoin>>

    suspend fun refresh()
}

class CashingCoinsRepository(private val coinDao: CoinDao, private val coinApiService:CoinApiService):CoinsRepository {
    override suspend fun getCoins(): Flow<List<CryptoCoin>>{
        return coinDao.getAllItems().map {
            it.asDomainCoins()
        }.onEach {
            if(it.isEmpty()){
                refresh()
            }
        }
    }

    override suspend fun refresh(){
        try {
            coinApiService.getCoinsAsFlow().asDomainObjects().collect {
                    value ->
                for(coin in value) {
                    coinDao.insert(coin.asDbCoin())
                }
            }
        }
        catch(e: SocketTimeoutException){
            //log something
        }
    }

}