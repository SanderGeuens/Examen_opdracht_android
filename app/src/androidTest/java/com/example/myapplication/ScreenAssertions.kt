package com.example.myapplication

import androidx.navigation.NavController
import junit.framework.Assert.assertEquals

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}