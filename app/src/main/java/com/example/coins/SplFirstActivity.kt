package com.example.coins

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class SplFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spl_first)

        Log.d("SplFirstActivity", "onCreate")

        Handler().postDelayed({
            try {
                Log.d("SplFirstActivity", "Loading coins data list")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.d("SplFirstActivity", "Error of loading coins data list")
                val intent = Intent(this, ErrorFirstActivity::class.java)
                startActivity(intent)
            }
            finally {
                Log.d("SplFirstActivity", "Finish")
                finish()
            }
        },3000)
    }
}