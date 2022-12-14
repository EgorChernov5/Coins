package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ErrorFirstActivity : AppCompatActivity() {
    private lateinit var currency: CharSequence
    private lateinit var chipsGroup: ChipGroup
    private lateinit var chip: Chip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_first)

        Log.d("Coin", "ErrorFirstActivity: onCreate")

        val image = findViewById<ImageView>(R.id.efa_image)
        Glide
            .with(this)
            .load(R.drawable.bitcoin)
            .into(image)

        // check currency
        val arguments = intent.extras
        if (arguments == null) {
            Log.d("Coin", "SplFirstActivity: Chip is usd")
            chipsGroup = findViewById(R.id.sfa_chips_group)
            chip = chipsGroup.findViewById(R.id.sfa_usd)
            chip.isChecked = true
            currency = chip.text
        } else {
            Log.d("Coin", "SplFirstActivity: Change chip")
            currency = arguments.getString("Currency").toString()
            chipsGroup = findViewById(R.id.efa_chips_group)
            chip = if (currency == "Eur") {
                chipsGroup.findViewById(R.id.efa_eur)
            } else {
                chipsGroup.findViewById(R.id.efa_usd)
            }
            chip.isChecked = true
        }

        chipsGroup.setOnCheckedStateChangeListener { chipGroup: ChipGroup,
                                                     checkedIds: MutableList<Int> ->
            Log.d("Coin", "ErrorFirstActivity: Change chip")
            currency = chipGroup.findViewById<Chip>(checkedIds[0]).text
        }
    }

    fun clickOnRefresh(view: View) {
        Log.d("Test", "ErrorFirstActivity: Try loading coins data list again")
        val intent = Intent(this, SplFirstActivity::class.java)
        intent.putExtra("Currency", currency)
        startActivity(intent)
        finish()
    }
}