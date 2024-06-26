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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CoinApiState {
    object Success : CoinApiState
    object Error : CoinApiState
    object Loading : CoinApiState
}

class CoinOverviewViewModel(private val coinsRepository:CoinsRepository) : ViewModel(){

    var coinApiState : CoinApiState by mutableStateOf(CoinApiState.Loading)
        private set

    lateinit var uiListState : StateFlow<CoinListState>

    init {
        getCoins()
    }

    private fun getCoins () {
        try {
            viewModelScope.launch { coinsRepository.refresh() }

            uiListState = coinsRepository.getCoins().map { CoinListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CoinListState()
                )
            coinApiState = CoinApiState.Success
        }
            catch (e: IOException){
                //show a toast? save a log on firebase? ...
                //set the error state
                coinApiState = CoinApiState.Error
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
data class CoinListState(val coinList: List<CryptoCoin> = listOf())