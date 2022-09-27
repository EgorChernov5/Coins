package com.example.coins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        val imageView: ImageView = findViewById(R.id.ca_coin_image)

        val arguments = intent.extras
        val coin: Coin
        if (arguments != null) {
            coin = arguments.getSerializable(Coin::class.java.simpleName) as Coin

            imageView.setImageResource(coin.imageCoinRes)
        }
    }

    fun clickOnBack(view: View) {
        finish()
    }
}