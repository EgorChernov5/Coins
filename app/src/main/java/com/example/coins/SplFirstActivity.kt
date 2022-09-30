package com.example.coins

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spl_first)

        Log.d("Coin", "SplFirstActivity: onCreate")

        Handler().postDelayed({
            val apiInterface = ApiInterface.create().getCoins()

            apiInterface.enqueue( object : Callback<Coins> {
                override fun onResponse(call: Call<Coins>, response: Response<Coins>) {
                    Log.d("Coin", "SplFirstActivity: Loading coins data list")
                    try {
                        if (response.body() == null) throw Exception("Empty response!")
                        val coins: Coins? = response.body()
                        val intent = Intent(this@SplFirstActivity, MainActivity::class.java)
                        intent.putExtra(Coins::class.java.simpleName, coins)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println(e.message)
                        val intent = Intent(this@SplFirstActivity, ErrorFirstActivity::class.java)
                        startActivity(intent)
                    } finally {
                        finish()
                    }
                }

                override fun onFailure(call: Call<Coins>, t: Throwable) {
                    Log.d("Coin", "SplFirstActivity: Request error!")
                    val intent = Intent(this@SplFirstActivity, ErrorFirstActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        },1000)
    }
}