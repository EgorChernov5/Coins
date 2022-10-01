package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.example.coins.apiCoin.Coin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplSecondActivity : AppCompatActivity() {
    private lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spl_second)

        Log.d("Coin", "SplSecondActivity: onCreate")

        val arguments = intent.extras
        if (arguments != null) {
            Log.d("Coin", "SplSecondActivity: Get coin id")
            id = arguments.getString("coinId").toString()
        } else {
            throw Exception("Arguments == null")
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val apiInterface = ApiInterface.create().getCoin(
                id,
                false,
                false,
                false,
                false,
                false)

            apiInterface.enqueue( object : Callback<Coin> {
                override fun onResponse(call: Call<Coin>, response: Response<Coin>) {
                    Log.d("Coin", "SplSecondActivity: Loading coins data list")
                    try {
                        if (response.body() == null) throw Exception("Empty response!")
                        val coin: Coin? = response.body()
                        val intent = Intent(this@SplSecondActivity, CoinActivity::class.java)
                        intent.putExtra(Coin::class.java.simpleName, coin)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println(e.message)
                        val intent = Intent(this@SplSecondActivity, ErrorSecondActivity::class.java)
                        startActivity(intent)
                    } finally {
                        finish()
                    }
                }

                override fun onFailure(call: Call<Coin>, t: Throwable) {
                    Log.d("Coin", "SplSecondActivity: Request error!")
                    val intent = Intent(this@SplSecondActivity, ErrorSecondActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        },1000)
    }

    fun clickOnBack(view: View) {
        finish()
    }
}