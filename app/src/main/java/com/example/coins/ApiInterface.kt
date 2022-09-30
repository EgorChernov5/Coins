package com.example.coins

import com.example.coins.apiCoin.Coin
import com.example.coins.apiCoins.Coins
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

    @GET("coins/{id}")
    fun getCoin(@Path("id") id: String,
                @Query("localization") localization: Boolean,
                @Query("tickers") tickers: Boolean,
                @Query("market_data") market_data: Boolean,
                @Query("community_data") community_data: Boolean,
                @Query("developer_data") developer_data: Boolean) : Call<Coin>

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