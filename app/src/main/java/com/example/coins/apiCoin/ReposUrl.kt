package com.example.coins.apiCoin

import java.io.Serializable

data class ReposUrl(
    val bitbucket: List<Any>,
    val github: List<String>
) : Serializable