package com.example.coins

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager

class SplSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spl_second)

        Log.d("SplSecondActivity", "onCreate")

        val arguments = intent.extras
        if (arguments != null) {
            Log.d("SplSecondActivity", "Loading a coin data")
            Handler().postDelayed({
                val coin = arguments.getSerializable(Coin::class.java.simpleName) as Coin
                val intent = Intent(this, CoinActivity::class.java)
                intent.putExtra(coin::class.java.simpleName, coin)
                startActivity(intent)
                finish()
            }, 3000)
        } else {
            Log.d("SplSecondActivity", "Lost data")
            finish()
        }
    }

    fun clickOnBack(view: View) {
        finish()
    }
}