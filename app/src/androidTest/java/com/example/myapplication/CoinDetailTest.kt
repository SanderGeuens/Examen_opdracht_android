package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.myapplication.navigation.NavigationRoutes
import com.example.myapplication.util.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinDetailTest {

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

    fun navigateToDetailFromCoin() {
        composeTestRule.onAllNodesWithTag("detailsButton").onFirst().performClick()
    }
    @Test
    fun testDetailScreen1() {
        composeTestRule.onNodeWithTag("detailsIcon").performClick()
        composeTestRule.onNodeWithText("Select a coin first").assertIsDisplayed()
    }

    @Test
    fun testDetailScreen2() {
        navigateToDetailFromCoin()
        composeTestRule.onNodeWithText("Details").assertIsDisplayed()
        composeTestRule.onNodeWithTag("coinDetailCard").assertIsDisplayed()
    }
}