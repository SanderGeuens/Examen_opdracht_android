package com.example.myapplication.screens.overviewscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.model.CryptoCoin
import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.screens.detailscreen.CoinDetailViewModel
import com.example.myapplication.util.UtilMethods
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinOverviewScreen (
    modifier: Modifier = Modifier,
    coinOverviewViewModel: CoinOverviewViewModel = viewModel(factory= CoinOverviewViewModel.Factory),
    coinDetailViewModel: CoinDetailViewModel,
    navigateToDetails:()->Unit,
) {

    val coinListState by coinOverviewViewModel.uiListState.collectAsState()
    val coinApiState = coinOverviewViewModel.coinApiState

    var searchTerm by rememberSaveable{ mutableStateOf("") }

    Column (
        modifier = modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text="Overview of cryptocoins",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = searchTerm,
            onValueChange = { searchTerm = it },
            label = { Text("search term") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).testTag("searchInput")
        )
        Spacer(modifier = Modifier.height(20.dp))
        when (coinApiState) {
            is CoinApiState.Loading -> LoadingScreen()
            is CoinApiState.Success -> CoinOverviewColumn(coins = coinListState.coinList, coinDetailViewModel = coinDetailViewModel, navigateToDetails = navigateToDetails, searchTerm = searchTerm)
            is CoinApiState.Error -> ErrorScreen()
        }

    }
}

@Composable
fun CoinOverviewColumn (
    modifier: Modifier = Modifier,
    coins:List<CryptoCoin>,
    //coinListState:CoinListState,
    coinDetailViewModel: CoinDetailViewModel,
    navigateToDetails:()->Unit,
    searchTerm:String = "",
) {
    var list:List<CryptoCoin> = coins
    if (searchTerm.isNullOrBlank()) {
        list = coins
    }else {
        list = coins.filter { item->item.name.uppercase().contains(searchTerm.uppercase()) }
    }
    LazyColumn() {
        items((list)) { item ->
            Spacer(modifier = Modifier.height(height=10.dp))
            CoinOverviewCard(coin = item,
                modifier = Modifier.padding(horizontal = 20.dp).testTag("coinOverviewCard"),
                coinDetailViewModel = coinDetailViewModel,
                navigateToDetails = navigateToDetails)
            Spacer(modifier = Modifier.height(height=10.dp))
        }
    }

}
@Composable
fun CoinOverviewCard(
    modifier: Modifier = Modifier,
    coin:CryptoCoin,
    coinDetailViewModel: CoinDetailViewModel,
    navigateToDetails:()->Unit,
) {

    val priceDouble: Double = UtilMethods.goFromStringToDoubleRounded(coin.priceUsd)

    Card(

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .height(130.dp)
            .fillMaxWidth().clip(RoundedCornerShape(18.dp))
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = coin.name,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .width(170.dp)
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "rank: ${coin.rank}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "price: $$priceDouble")
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.width(140.dp).padding(end=13.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {coinDetailViewModel.updateUiState(
                        id = coin.id,
                        rank = coin.rank,
                        symbol = coin.symbol,
                        name = coin.name,
                        supply = coin.supply,
                        maxSupply = coin.maxSupply,
                        marketCapUsd = coin.marketCapUsd,
                        volumeUsd24Hr = coin.volumeUsd24Hr,
                        priceUsd = coin.priceUsd,
                        changePercent24Hr = coin.changePercent24Hr,
                        vwap24Hr = coin.vwap24Hr
                    );navigateToDetails()},
                    modifier = Modifier.testTag("detailsButton")
                ) {
                    Text("More details")
                }
            }
        }
    }
}

    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

    @Composable
    fun ErrorScreen(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = ""
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        }
    }
