package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val button = findViewById<ImageView>(R.id.back_button)
        button.visibility = ImageView.VISIBLE
        button.setOnClickListener {
            finish()
        }

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