package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.coins.apiCoin.Coin

class ErrorSecondActivity : AppCompatActivity() {
    private lateinit var coin: Coin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_second)

        val textView = findViewById<TextView>(R.id.esa_coin_name)

        val arguments = intent.extras
        if (arguments != null) {
            Log.d("Coin", "ErrorSecondActivity: Get coin name")
            coin = arguments.getSerializable(Coin::class.java.simpleName) as Coin
            textView.text = coin.name
        } else {
            throw Exception("Arguments == null")
        }

        val image = findViewById<ImageView>(R.id.esa_image)
        Glide
            .with(this)
            .load(R.drawable.bitcoin)
            .into(image)
    }

    fun clickOnRefresh(view: View) {
        Log.d("ErrorSecondActivity", "Try loading coin data again")
        val intent = Intent(this, SplSecondActivity::class.java)
        intent.putExtra("coinId", coin.id)
        intent.putExtra("coinName", coin.name)
        startActivity(intent)
        finish()
    }

    fun clickOnBack(view: View) {
        finish()
    }
}