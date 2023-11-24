package com.example.myapplication.navigation.navidrawer

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.navigation.NavigationRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    navigateTo:(String)->Unit,
    selectedItemIndex: Int,
    scope: CoroutineScope,
    drawerState: DrawerState,
    context: Context,
) {
    var selectedItemIdx = selectedItemIndex

    val navItems = listOf(
        NavigationItem(
            title = stringResource(id = NavigationRoutes.CoinOverview.title),
            to = NavigationRoutes.CoinOverview.name,
        ),
        NavigationItem(
            title = stringResource(id = NavigationRoutes.CoinDetail.title),
            to = NavigationRoutes.CoinDetail.name,
        ),
        NavigationItem(
            title = stringResource(id = NavigationRoutes.CoinFavorites.title),
            to = NavigationRoutes.CoinFavorites.name,
        )
    )

    ModalDrawerSheet(
        drawerContentColor = Color.White,
        modifier = Modifier
            .alpha(0.9f),
    ) {
        Spacer(
            modifier = Modifier.height(16.dp),
        )

        navItems.forEachIndexed { index, item ->
            Divider(
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
            )
            val isSelected = index == selectedItemIndex

            NavigationDrawerItem(
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color.Transparent,
                ),
                label = {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Text(
                            text = item.title.uppercase(),
                            maxLines = 2,
                            fontSize = 30.sp,
                            lineHeight = 35.sp,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                },
                selected = index == selectedItemIndex,
                onClick = {
                    navigateTo(item.to)
                    selectedItemIdx = index
                    scope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier
                    .height(80.dp)
                    .padding(NavigationDrawerItemDefaults.ItemPadding),
            )
        }
    }
}