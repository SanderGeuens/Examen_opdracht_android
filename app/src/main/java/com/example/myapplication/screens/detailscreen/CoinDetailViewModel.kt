package com.example.myapplication.screens.detailscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DetailUiState(
    val id: String = "",
    val rank: String ="",
    val symbol: String ="",
    val name: String ="",
    val supply: String ="",
    val maxSupply: String? ="",
    val marketCapUsd: String ="",
    val volumeUsd24Hr: String ="",
    val priceUsd: String ="",
    val changePercent24Hr: String ="",
    val vwap24Hr: String? ="",
)
class CoinDetailViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun updateUiState (
        id: String  ,
        rank: String ,
        symbol: String ,
        name: String ,
         supply: String ,
         maxSupply: String? ,
         marketCapUsd: String ,
         volumeUsd24Hr: String ,
        priceUsd: String ,
        changePercent24Hr: String ,
        vwap24Hr: String? ,
    ) {
        _uiState.update {
            currentState -> currentState.copy(
                id = id,
                rank = rank,
                symbol = symbol,
                name= name,
                supply = supply,
                maxSupply =maxSupply,
                marketCapUsd = marketCapUsd,
                volumeUsd24Hr = volumeUsd24Hr,
                priceUsd = priceUsd,
                changePercent24Hr = changePercent24Hr,
                vwap24Hr = vwap24Hr,
            )
        }
    }


}