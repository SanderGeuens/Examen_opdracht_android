package com.example.myapplication.fake

import com.example.myapplication.data.CashingCoinsRepository
import com.example.myapplication.data.database.CoinDb
import junit.framework.TestCase.assertEquals

import kotlinx.coroutines.test.runTest
import org.junit.Test


class NetworkCoinsRepositoryTest {

    @Test
    fun networkCoinsRepository_getCoins_verifyCoinsList() = runTest(){

        val repository = CashingCoinsRepository(
            coinApiService = FakeCoinApiService()
        )
        assertEquals(FakeDataSource.coinList, repository.getCoins())
    }


}