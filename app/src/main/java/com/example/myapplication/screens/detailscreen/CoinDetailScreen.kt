package com.example.myapplication.screens.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinDetailScreen (
    modifier : Modifier = Modifier,
) {

    Column (
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text="Details", textAlign = TextAlign.Center, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        CoinDetailCard()
    }
}
@Composable
fun CoinDetailCard(
    modifier : Modifier = Modifier,
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .height(470.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="Bitcoin",
            fontSize = 26.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="rank :1",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="price :33",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="priceChange1h: 0.46",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="priceChange1d: 0.26",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="priceChange1w: 0.49",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="volume: 55545343423232",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="market cap: 37587538146.52646",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="available supply: 153856150",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="total supply: 153856150",
            fontSize = 19.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button (
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        ) {
            Text(text="Add to favorites")
        }
    }
}