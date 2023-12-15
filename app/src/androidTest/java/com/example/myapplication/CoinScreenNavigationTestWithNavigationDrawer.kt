package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.myapplication.navigation.NavigationRoutes
import com.example.myapplication.util.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinScreenNavigationTestWithNavigationDrawer{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CoinApp(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER, navController = navController)
        }
    }

    @Test
    fun coinOverview_navigateToDetailsFromDraer_verifyRoute() {
        composeTestRule.onAllNodesWithTag("drawerIcon")[1].performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinDetail.name)
    }
    private fun navigateToDetailFromDrawer() {
        composeTestRule.onAllNodesWithTag("drawerIcon")[1].performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinDetail.name)
    }
    @Test
    fun coinOverview_navigateToOverviewFromDrawer_verifyRoute() {
        navigateToDetailFromDrawer()
        composeTestRule.onAllNodesWithTag("drawerIcon")[0].performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinOverview.name)
    }
}