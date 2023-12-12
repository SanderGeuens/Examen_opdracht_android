package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.myapplication.navigation.NavigationRoutes
import com.example.myapplication.util.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinScreenNavigationTestWithBottomNavigation{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CoinApp(navigationType = NavigationType.BOTTOM_NAVIGATION, navController = navController)
        }
    }

    @Test
    fun coinNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(NavigationRoutes.CoinOverview.name)
    }

    @Test
    fun coinNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        composeTestRule.onNodeWithTag("navigateBackIcon").assertDoesNotExist()
    }

    @Test
    fun coinOverview_NavigateToDetailFromCoin_verifyRoute() {
        composeTestRule.onAllNodesWithTag("detailsButton").onFirst().performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinDetail.name)
    }

    @Test
    fun coinOverview_NavigateToDetailFromBottomBar_verifyRoute() {
        composeTestRule.onNodeWithTag("detailsIcon").performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinDetail.name)
    }
    private fun navigateToDetailsFromBottomBar() {
        composeTestRule.onNodeWithTag("detailsIcon").performClick()
    }
    @Test
    fun coinDetails_NavigateUp_verifyRoute() {
        navigateToDetailsFromBottomBar()
        composeTestRule.onNodeWithTag("navigateBackIcon").performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinOverview.name)
    }
    @Test
    fun coinDetails_NavigateToOverviewFromBottomBar_verifyRoute() {
        navigateToDetailsFromBottomBar()
        composeTestRule.onNodeWithTag("overviewIcon").performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinOverview.name)
    }
}