package com.example.coins

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("coins/markets?vs_currency=usd&per_page=10&page=1&sparkline=false&price_change_percentage=1h")
    fun getCoins() : Call<Coins>

//    @GET("coins/{id}?localization=false&tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false")
//    fun getCoin() : Call<List<Coin>>

    companion object {
        var BASE_URL = "https://api.coingecko.com/api/v3/"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}