package com.example.myapplication.fake

import com.example.myapplication.network.ApiCryptoCoin
import com.example.myapplication.network.CoinsResponse

object FakeDataSource {
    const val id1 ="bitcoin"
    const val rank1="1"
    const val symbol1="BTC"
    const val name1="Bitcoin"
    const val supply1 ="19558400.00"
    const val maxSupply1 ="21000000.00"
    const val marketCapUsd1="757539556491.7177555414732800"
    const val volumeUsd24Hr1="6794911175.9678218212699686"
    const val priceUsd1="38732.1844574053989867"
    const val changePercent24Hr1="2.7955228871365923"
    const val vwap24Hr1="38493.1113582572600648"
    const val explorer1="https://blockchain.info/"

    const val id2="ethereum"
    const val rank2="2"
    const val symbol2="ETH"
    const val name2="Ethereum"
    const val supply2="120237836.3423665800000000"
    const val maxSupply2="166801148.000"
    const val marketCapUsd2="250882230635.1576956074022011"
    const val volumeUsd24Hr2="3946448269.0736708138717184"
    const val priceUsd2="2086.5497772331230610"
    const val changePercent24Hr2="2.1193400357841944"
    const val vwap24Hr2="2089.2487304154042773"
    const val explorer2="https://etherscan.io/"

    val coinList = listOf(
        ApiCryptoCoin(id1,rank1, symbol1, name1, supply1, maxSupply1, marketCapUsd1, volumeUsd24Hr1,
            priceUsd1, changePercent24Hr1, vwap24Hr1, explorer1),
        ApiCryptoCoin(id2, rank2, symbol2, name2, supply2, maxSupply2, marketCapUsd2, volumeUsd24Hr2,
            priceUsd2, changePercent24Hr2, vwap24Hr2, explorer2)
    )

    val coinResponse = CoinsResponse(coinList,2234432233445544)
}