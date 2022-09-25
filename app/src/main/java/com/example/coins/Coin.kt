package com.example.coins

import java.io.Serializable

data class Coin(private val _name: String, private val _price: Float, private val _flagRes: Int): Serializable {
    val name: String
        get() = _name
    val price: Float
        get() = _price
    val flagRes: Int
        get() = _flagRes
}