package com.example.coins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    var currency: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chipsGroup = findViewById<ChipGroup>(R.id.chips_group)
        val chip = chipsGroup.findViewById<Chip>(R.id.usd)
        chip.isChecked = true
        currency = chip.text

        chipsGroup.setOnCheckedChangeListener { chipGroup: ChipGroup, checkedId: Int ->
            currency = chipGroup.findViewById<Chip>(checkedId)?.text
            Toast.makeText(chipGroup.context, currency ?: "No Choice", Toast.LENGTH_SHORT).show()
        }
    }
}