package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ErrorFirstActivity : AppCompatActivity() {
    var currency: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_first)

        Log.d("ErrorFirstActivity", "onCreate")

        val image = findViewById<ImageView>(R.id.efa_image)
        Glide
            .with(this)
            .load(R.drawable.bitcoin)
            .into(image)

        val chipsGroup = findViewById<ChipGroup>(R.id.efa_chips_group)
        val chip = chipsGroup.findViewById<Chip>(R.id.efa_usd)
        currency = chip.text

        Log.d("ErrorFirstActivity", "Initial chips")

        chipsGroup.setOnCheckedChangeListener { chipGroup: ChipGroup, checkedId: Int ->
            Log.d("ErrorFirstActivity", "Change chip")
            currency = chipGroup.findViewById<Chip>(checkedId)?.text
            Toast.makeText(chipGroup.context, currency ?: "No Choice", Toast.LENGTH_SHORT).show()
        }
    }

    fun clickOnRefresh(view: View) {
        Log.d("ErrorFirstActivity", "Try loading coins data list again")
        val intent = Intent(this, SplFirstActivity::class.java)
        startActivity(intent)
        finish()
    }
}