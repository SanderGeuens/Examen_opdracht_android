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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CoinUiState {
    object Success : CoinUiState
    object Error : CoinUiState
    object Loading : CoinUiState
}

class CoinOverviewViewModel(private val coinsRepository:CoinsRepository) : ViewModel(){

    var coinUiState : CoinUiState by mutableStateOf(CoinUiState.Loading)
        private set

    lateinit var uiListState : StateFlow<CoinListState>

    init {
        getCoins()
    }

    private fun getCoins () {
        try {
            viewModelScope.launch { coinsRepository.refresh() }

            uiListState = coinsRepository.getCoins().map { TaskListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = TaskListState()
                )
            coinUiState = CoinUiState.Success
        }
            catch (e: IOException){
                //show a toast? save a log on firebase? ...
                //set the error state
                coinUiState = CoinUiState.Error
            }
    }
        /*
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
        */
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
data class CoinListState(val coinList: List<CryptoCoin> = listOf())