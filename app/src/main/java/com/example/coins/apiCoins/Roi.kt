package com.example.coins.apiCoins

import java.io.Serializable

data class Roi(
    val currency: String,
    val percentage: Double,
    val times: Double
) : Serializable