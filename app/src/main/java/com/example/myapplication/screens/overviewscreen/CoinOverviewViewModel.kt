package com.example.myapplication.screens.overviewscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.network.CoinApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CoinUiState {
    data class Success(val coins: List<ApiCryptoCoin>) : CoinUiState
    object Error : CoinUiState
    object Loading : CoinUiState
}

class CoinOverviewViewModel : ViewModel(){

    var coinUiState : CoinUiState by mutableStateOf(CoinUiState.Loading)
        private set

    init {
        getCoins()
    }

    private fun getCoins () {
        viewModelScope.launch {

            coinUiState = try {
                val result = CoinApi.retrofitService.getCoins().data
                //CoinUiState.Success(listResult.data.size.toString())
                CoinUiState.Success(result)
            }catch (e: IOException) {
                CoinUiState.Error
            }



        }
    }

}