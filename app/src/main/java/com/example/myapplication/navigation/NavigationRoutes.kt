package com.example.myapplication.navigation

import androidx.annotation.StringRes
import com.example.myapplication.R

enum class NavigationRoutes(@StringRes val title: Int) {

    CoinOverview(title= R.string.coin_overview_title),
    CoinDetail(title= R.string.coin_detail_title),
    CoinFavorites(title=R.string.coin_favorites_title),
}