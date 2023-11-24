package com.example.myapplication

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.NavigationRoutes
import com.example.myapplication.navigation.navidrawer.NavigationDrawer
import com.example.myapplication.screens.detailscreen.CoinDetailScreen
import com.example.myapplication.screens.favoritescreen.CoinFavoritesScreen
import com.example.myapplication.screens.overviewscreen.CoinOverviewCard
import com.example.myapplication.screens.overviewscreen.CoinOverviewScreen
import com.example.myapplication.screens.overviewscreen.CoinOverviewViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinApp (
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {

        val backStackEntry by navController.currentBackStackEntryAsState()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        val coinOverviewViewModel: CoinOverviewViewModel = viewModel(/*factory= CoinOverviewViewModel.Factory*/)



        ModalNavigationDrawer(
            gesturesEnabled = true, // Swipe voor navigatiebar
            drawerContent = {
                NavigationDrawer(
                    navigateTo = {navController.navigate(it)},
                    selectedItemIndex = selectedItemIndex,
                    drawerState = drawerState,
                    scope = scope,
                    context = context,
                )
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                topBar = {
                    AppBar(
                        currentScreen = NavigationRoutes.valueOf(
                            backStackEntry?.destination?.route ?: NavigationRoutes.CoinOverview.name,
                        ),
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = {navController.navigateUp()},
                        openDrawer = {scope.launch {
                            drawerState.open()
                        }},
                    )
                }
            ) {
                    innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = NavigationRoutes.CoinOverview.name,
                    modifier = Modifier.padding(innerPadding)
                ){
                    composable(NavigationRoutes.CoinOverview.name) {
                        CoinOverviewScreen(coinUiState= coinOverviewViewModel.coinUiState)
                        //CoinOverviewCard()
                    }
                    composable(NavigationRoutes.CoinDetail.name){
                        CoinDetailScreen()
                    }
                    composable(NavigationRoutes.CoinFavorites.name){
                        CoinFavoritesScreen()
                    }
                }
            }
        }
    }
}