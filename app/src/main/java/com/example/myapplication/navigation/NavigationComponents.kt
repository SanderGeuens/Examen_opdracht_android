package com.example.myapplication.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination

@Composable
fun CoinBottomAppBar(goToOverview: () -> Unit, goToDetail: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            IconButton(
                onClick = goToOverview,
                modifier = Modifier.testTag("overviewIcon")
            ) {
                Icon(Icons.Filled.List, contentDescription = "navigate to overview screen")
            }

            IconButton(
                onClick = goToDetail,
                modifier = Modifier.testTag("detailsIcon")
            ) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "navigate to detail page",
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (navItem in NavigationRoutes.values()) {
            NavigationDrawerItem(
                selected = selectedDestination?.route == navItem.name,
                label = {
                    Text(
                        text = navItem.name,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                },
                icon = {

                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.name) },
                modifier = Modifier.testTag("drawerIcon")
            )
        }
    }
}

@Composable
fun CoinNavigationRail(selectedDestination: NavDestination?, onTabPressed: (String) -> Unit, modifier : Modifier = Modifier) {

    NavigationRail(modifier = modifier) {
        for (navItem in NavigationRoutes.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                modifier = Modifier.testTag("railIcon"),
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name
                    )
                }
            )

        }
    }
}