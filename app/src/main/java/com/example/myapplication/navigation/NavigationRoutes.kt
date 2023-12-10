package com.example.myapplication.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R

enum class NavigationRoutes(@StringRes val title: Int, val icon: ImageVector) {

    CoinOverview(title= R.string.coin_overview_title, Icons.Filled.List),
    CoinDetail(title= R.string.coin_detail_title,Icons.Filled.Info),
    CoinFavorites(title=R.string.coin_favorites_title,Icons.Filled.Info),
}