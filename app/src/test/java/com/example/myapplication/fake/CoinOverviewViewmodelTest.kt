package com.example.myapplication.fake

import com.example.myapplication.fake.rules.TestDispatcherRule
import com.example.myapplication.screens.overviewscreen.CoinOverviewViewModel
import com.example.myapplication.screens.overviewscreen.CoinUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class CoinOverviewViewmodelTest {


    @get:Rule
    val testDispatcher = TestDispatcherRule()


    @Test
    fun coinOverviewViewModel_getCoins_verifyCoinUiStateSuccess() =
        runTest{
            val coinOverviewViewModel = CoinOverviewViewModel(coinsRepository = FakeCoinsRepository())
            assertEquals(
                CoinUiState.Success(FakeDataSource.coinList),
                coinOverviewViewModel.coinUiState
            )
        }
}