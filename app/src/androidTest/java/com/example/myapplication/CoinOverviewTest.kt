package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.myapplication.util.NavigationType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinOverviewTest{
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
    fun testOverviewScreen ()= runTest(){

        composeTestRule.onNodeWithText("Overview of cryptocoins").assertIsDisplayed()
        composeTestRule.onNodeWithTag("searchInput").assertIsDisplayed()
        delay(5000)
        composeTestRule.onAllNodesWithTag("coinOverviewCard").onFirst().assertIsDisplayed()
    }
}