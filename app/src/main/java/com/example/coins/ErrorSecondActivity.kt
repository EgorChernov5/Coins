package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class ErrorSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_second)

        val image = findViewById<ImageView>(R.id.esa_image)
        Glide
            .with(this)
            .load(R.drawable.bitcoin)
            .into(image)
    }

    fun clickOnRefresh(view: View) {
        Log.d("ErrorSecondActivity", "Try loading coin data again")
        val intent = Intent(this, SplSecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun clickOnBack(view: View) {
        Log.d("ErrorSecondActivity", "Back to MainActivity")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}