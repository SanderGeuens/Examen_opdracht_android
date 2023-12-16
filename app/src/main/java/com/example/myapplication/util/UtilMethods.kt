package com.example.myapplication.util

import java.math.RoundingMode

public class UtilMethods{

    companion object {
        fun goFromStringToDoubleRounded (numberInString:String):Double {
            return numberInString.toDouble().toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
        }
    }
}