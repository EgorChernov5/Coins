package com.example.coins

import java.io.Serializable

data class ReposUrl(
    val bitbucket: List<Any>,
    val github: List<String>
) : Serializable