package com.example.myapplication.util

import org.junit.Test
import org.junit.Assert.*

class UtilMethodsTest {

    @Test
    fun testFromStringToDoubleRounded() {
        val emptyMutableMap = mutableMapOf<String, Double>()
        emptyMutableMap["1.34654"] = 1.35
        emptyMutableMap["55.67454"] = 55.67
        emptyMutableMap["1.34554"] = 1.35
        emptyMutableMap["1.34354"] = 1.34
        emptyMutableMap["-1.34654"] = -1.35
        emptyMutableMap["-1.34454"] = -1.34
        emptyMutableMap["-1.34554"] = -1.35

        for (key in emptyMutableMap.keys) {
            val calculatedDouble = UtilMethods.goFromStringToDoubleRounded(key)
            assertEquals(calculatedDouble,emptyMutableMap[key])
        }
    }
}