package com.example.coins

import java.io.Serializable

data class Coin(private val _name: String,
                private val _shortName: String,
                private val _price: String,
                private val _percent: String,
                private val _imageCoinRes: Int): Serializable {
    val name: String
        get() = _name
    val shortName: String
        get() = _shortName
    val price: String
        get() = _price
    val percent: String
        get() = _percent
    val imageCoinRes: Int
        get() = _imageCoinRes
}