package com.example.coins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        val imageView: ImageView = findViewById(R.id.flag)
        val nameView: TextView = findViewById(R.id.name)
        val priceView: TextView = findViewById(R.id.price)

        val arguments = intent.extras
        val coin: Coin
        if (arguments != null) {
            coin = arguments.getSerializable(Coin::class.java.simpleName) as Coin

            imageView.setImageResource(coin.flagRes)
            nameView.text = coin.name
            priceView.text = coin.price.toString()
        }
    }
}