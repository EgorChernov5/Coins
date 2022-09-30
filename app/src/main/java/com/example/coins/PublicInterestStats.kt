package com.example.coins

import java.io.Serializable

data class PublicInterestStats(
    val alexa_rank: Int,
    val bing_matches: Any
) : Serializable