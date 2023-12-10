package com.example.myapplication

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.CoinBottomAppBar
import com.example.myapplication.navigation.CoinNavigationRail
import com.example.myapplication.navigation.NavigationDrawerContent
import com.example.myapplication.navigation.NavigationRoutes
import com.example.myapplication.navigation.navidrawer.NavigationDrawer
import com.example.myapplication.screens.detailscreen.CoinDetailScreen
import com.example.myapplication.screens.detailscreen.CoinDetailViewModel
import com.example.myapplication.screens.favoritescreen.CoinFavoritesScreen
import com.example.myapplication.screens.overviewscreen.CoinOverviewCard
import com.example.myapplication.screens.overviewscreen.CoinOverviewScreen
import com.example.myapplication.screens.overviewscreen.CoinOverviewViewModel
import com.example.myapplication.util.NavigationType
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinApp (
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
    navigationType:NavigationType,
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

        val coinDetailViewModel: CoinDetailViewModel = viewModel()

        val goToOverview:()->Unit = {navController.popBackStack(NavigationRoutes.CoinOverview.name,inclusive = false)}
        val goToDetail = { navController.navigate(NavigationRoutes.CoinDetail.name)  {launchSingleTop = true} }

        if (navigationType == NavigationType.BOTTOM_NAVIGATION) {

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
                    },
                    bottomBar ={ CoinBottomAppBar(goToOverview = goToOverview,goToDetail=goToDetail)}
                ) {
                        innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.CoinOverview.name,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(NavigationRoutes.CoinOverview.name) {
                            CoinOverviewScreen(coinDetailViewModel = coinDetailViewModel, navigateToDetails = {navController.navigate(NavigationRoutes.CoinDetail.name)})
                        }
                        composable(NavigationRoutes.CoinDetail.name){
                            CoinDetailScreen(coinDetailViewModel = coinDetailViewModel)
                        }
                        composable(NavigationRoutes.CoinFavorites.name){
                            CoinFavoritesScreen()
                        }
                    }
                }
            
        }
        if(navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {


            PermanentNavigationDrawer(drawerContent = {
                PermanentDrawerSheet(Modifier.width(260.dp)) {

                    NavigationDrawerContent(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = {node: String -> navController.navigate(node)},
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(8.dp)
                    )
                }
            }){
                Scaffold(
                    topBar = {
                        AppBar(
                            currentScreen = NavigationRoutes.valueOf(
                                backStackEntry?.destination?.route
                                    ?: NavigationRoutes.CoinOverview.name,
                            ),
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() },
                            openDrawer = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.CoinOverview.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavigationRoutes.CoinOverview.name) {
                            CoinOverviewScreen(
                                coinDetailViewModel = coinDetailViewModel,
                                navigateToDetails = { navController.navigate(NavigationRoutes.CoinDetail.name) })
                        }
                        composable(NavigationRoutes.CoinDetail.name) {
                            CoinDetailScreen(coinDetailViewModel = coinDetailViewModel)
                        }
                        composable(NavigationRoutes.CoinFavorites.name) {
                            CoinFavoritesScreen()
                        }
                    }
                }
            }
        }
        else if(navigationType == NavigationType.NAVIGATION_RAIL)
        {
            Row {
                AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                    val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                    CoinNavigationRail(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                    )
                }

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
                    },

                ) {
                        innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.CoinOverview.name,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(NavigationRoutes.CoinOverview.name) {
                            CoinOverviewScreen(coinDetailViewModel = coinDetailViewModel, navigateToDetails = {navController.navigate(NavigationRoutes.CoinDetail.name)})
                        }
                        composable(NavigationRoutes.CoinDetail.name){
                            CoinDetailScreen(coinDetailViewModel = coinDetailViewModel)
                        }
                        composable(NavigationRoutes.CoinFavorites.name){
                            CoinFavoritesScreen()
                        }
                    }

                }
            }
        }

    }
}