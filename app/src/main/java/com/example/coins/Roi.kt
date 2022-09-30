package com.example.coins

import java.io.Serializable

data class Roi(
    val currency: String,
    val percentage: Double,
    val times: Double
) : Serializable