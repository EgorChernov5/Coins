package com.example.coins.apiCoin

import java.io.Serializable

data class X(
    val contract_address: String,
    val decimal_place: Any
) : Serializable