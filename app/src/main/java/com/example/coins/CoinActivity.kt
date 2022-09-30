package com.example.coins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        Log.d("CoinActivity", "onCreate")

        val image = findViewById<ImageView>(R.id.ca_coin_image)
        val description = findViewById<TextView>(R.id.ca_description)
        val categories = findViewById<TextView>(R.id.ca_categories)

        val arguments = intent.extras
        if (arguments != null) {
            // get the coin by id
            val coin = arguments.getSerializable(Coin::class.java.simpleName) as Coin
            // set a view value
            Glide
                .with(this)
                .load(coin.image.large)
                .into(image)
            description.text = coin.description.en
            categories.text = coin.categories.toString().drop(1).dropLast(1)
        }
    }

    fun clickOnBack(view: View) {
        finish()
    }
}