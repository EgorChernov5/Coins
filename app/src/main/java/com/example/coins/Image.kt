package com.example.coins

import java.io.Serializable

data class Image(
    val large: String,
    val small: String,
    val thumb: String
) : Serializable