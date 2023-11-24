package com.example.myapplication.network

import kotlinx.serialization.Serializable

@Serializable
data class InfoCoinsResponse (
    val coins_num: Int,
    val time: Int
){
}