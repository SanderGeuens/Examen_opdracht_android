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


class CoinScreenNavigationTestWithNavigationRail{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CoinApp(navigationType = NavigationType.NAVIGATION_RAIL, navController = navController)
        }
    }

    @Test
    fun coinOverview_navigateToDetailsFromRail_verifyRoute() {
        composeTestRule.onAllNodesWithTag("railIcon")[1].performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinDetail.name)
    }
    private fun navigateToDetailFromRail() {
        composeTestRule.onAllNodesWithTag("railIcon")[1].performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinDetail.name)
    }
    @Test
    fun coinOverview_navigateToOverviewFromRail_verifyRoute() {
        navigateToDetailFromRail()
        composeTestRule.onAllNodesWithTag("railIcon")[0].performClick()
        navController.assertCurrentRouteName(NavigationRoutes.CoinOverview.name)
    }
}