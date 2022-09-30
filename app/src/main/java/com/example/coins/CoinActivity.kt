package com.example.coins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        Log.d("CoinActivity", "onCreate")

        val imageView: ImageView = findViewById(R.id.ca_coin_image)

//        val arguments = intent.extras
//        if (arguments != null) {
//            val coin = arguments.getSerializable(Coin::class.java.simpleName) as Coin
//
//            imageView.setImageResource(coin.imageCoinRes)
//        }
    }

    fun clickOnBack(view: View) {
        finish()
    }
}