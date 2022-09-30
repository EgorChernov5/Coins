package com.example.coins

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("coins/markets")
    fun getCoins(@Query("vs_currency") vs_currency: String,
                 @Query("per_page") per_page: Int,
                 @Query("page") page: Int,
                 @Query("sparkline") sparkline: Boolean,
                 @Query("price_change_percentage") price_change_percentage: String,
                 ) : Call<Coins>

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