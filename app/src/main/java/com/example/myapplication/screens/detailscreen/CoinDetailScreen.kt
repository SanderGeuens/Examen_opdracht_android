package com.example.myapplication.screens.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.util.UtilMethods
import java.math.RoundingMode

@Composable
fun CoinDetailScreen (
    modifier : Modifier = Modifier,
    coinDetailViewModel: CoinDetailViewModel,
) {

    val detailUiState by coinDetailViewModel.uiState.collectAsState()

    Column (
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (detailUiState.id.isNotEmpty()) {
                Text(text="Details", textAlign = TextAlign.Center, fontSize = 30.sp)
            }else {
                Text(text="Select a coin first", textAlign = TextAlign.Center, fontSize = 30.sp)
            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        if (detailUiState.id.isNotEmpty()) {
            CoinDetailCard(
                id = detailUiState.id,
                rank = detailUiState.rank,
                symbol = detailUiState.symbol,
                name = detailUiState.name,
                supply = detailUiState.supply,
                maxSupply = detailUiState.maxSupply,
                marketCapUsd = detailUiState.marketCapUsd,
                volumeUsd24Hr = detailUiState.volumeUsd24Hr,
                priceUsd = detailUiState.priceUsd,
                changePercent24Hr = detailUiState.changePercent24Hr,
                vwap24Hr = detailUiState.vwap24Hr,
            )
        }

    }
}
@Composable
fun CoinDetailCard(
    modifier : Modifier = Modifier,
    id: String ,
    rank: String,
    symbol: String,
    name: String,
    supply: String,
    maxSupply: String?,
    marketCapUsd: String,
    volumeUsd24Hr: String,
    priceUsd: String,
    changePercent24Hr: String,
    vwap24Hr: String?,
) {
    /*
    var priceDouble: Double = priceUsd.toDouble()
    priceDouble = priceDouble.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()

    var  changePercent24HrDouble: Double = changePercent24Hr.toDouble()
    changePercent24HrDouble =changePercent24HrDouble.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()

    var supplyDouble: Double = supply.toDouble()
    supplyDouble = supplyDouble.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    */
    val priceDouble: Double = UtilMethods.goFromStringToDouble(priceUsd)

    val  changePercent24HrDouble: Double = UtilMethods.goFromStringToDouble(changePercent24Hr)

    val supplyDouble: Double = UtilMethods.goFromStringToDouble(supply)


    var maxSupplyDouble: Double? = null
    if (maxSupply != null) {
        maxSupplyDouble = UtilMethods.goFromStringToDouble(maxSupply)
    }

    val marketCapUsdDouble: Double = UtilMethods.goFromStringToDouble(marketCapUsd)

    val volumeUsd24HrDouble: Double = UtilMethods.goFromStringToDouble(volumeUsd24Hr)

    var vwap24HrDouble: Double?  = null
    if (vwap24Hr !=null) {
        vwap24HrDouble = UtilMethods.goFromStringToDouble(vwap24Hr)
    }

    Card (

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="$name",
            fontSize = 26.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="rank: $rank",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="symbol: $symbol",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="price: $$priceDouble",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="change percent 24 hour: $changePercent24HrDouble",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="supply: $supplyDouble",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

            Text(
                text="max supply: ${maxSupplyDouble?:"/"}",
                fontSize = 19.sp,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

        Text(
            text="market cap usd: $marketCapUsdDouble",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="volume usd 24 hour: $volumeUsd24HrDouble",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text="vwap 24H hour: ${vwap24HrDouble?:"/"}",
                fontSize = 19.sp,
                modifier = Modifier.padding(horizontal = 30.dp)
            )

        Spacer(modifier = Modifier.height(20.dp))
        Button (
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(text="Add to favorites")
        }
    }
}