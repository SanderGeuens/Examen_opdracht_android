package com.example.myapplication.screens.overviewscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.CoinApplication
import com.example.myapplication.data.CoinsRepository
import com.example.myapplication.model.CryptoCoin
import com.example.myapplication.network.ApiCryptoCoin
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CoinUiState {
    data class Success(val coins: List<CryptoCoin>) : CoinUiState
    object Error : CoinUiState
    object Loading : CoinUiState
}

class CoinOverviewViewModel(private val coinsRepository:CoinsRepository) : ViewModel(){

    var coinUiState : CoinUiState by mutableStateOf(CoinUiState.Loading)
        private set

    init {
        getCoins()
    }

    private fun getCoins () {
        viewModelScope.launch {

            coinUiState = try {
                //val coinsRepository = NetworkCoinsRepository()
                val result = coinsRepository.getCoins()
                //CoinUiState.Success(listResult.data.size.toString())
                CoinUiState.Success(result)
            }catch (e: IOException) {
                CoinUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CoinApplication)
                val coinsRepository = application.container.coinsRepository
                CoinOverviewViewModel(coinsRepository = coinsRepository)
            }
        }
    }

}