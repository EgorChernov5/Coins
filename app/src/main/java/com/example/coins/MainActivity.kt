package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    private lateinit var coins: ArrayList<CoinsItem>
    private lateinit var currency: CharSequence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Test", "MainActivity: onCreate")

        val chipsGroup = findViewById<ChipGroup>(R.id.ma_chips_group)
        val chip = chipsGroup.findViewById<Chip>(R.id.ma_usd)
        chip.isChecked = true
        currency = chip.text

        chipsGroup.setOnCheckedChangeListener { chipGroup: ChipGroup, checkedId: Int ->
            Log.d("Test", "MainActivity: Change chip")
            currency = chipGroup.findViewById<Chip>(checkedId).text
            Toast.makeText(chipGroup.context, currency, Toast.LENGTH_SHORT).show()
        }

//        Set initial data()
        val arguments = intent.extras
        if (arguments != null) {
            coins = arguments.getSerializable(Coins::class.java.simpleName) as ArrayList<CoinsItem>
        }

        val recyclerView: RecyclerView = findViewById(R.id.ma_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val coinAdapter = CoinAdapter(coins)
        recyclerView.adapter = coinAdapter

        coinAdapter.onItemClick = {
            Log.d("Test", "MainActivity: Click on coin")
            // Выбираем элемент
            val coin = it
            // Выводим результат
            Toast.makeText(this, coin.toString(), Toast.LENGTH_SHORT).show()
            // Переходим к CoinActivity через SplSecondActivity
            val intent = Intent(this, SplSecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.putExtra(it::class.java.simpleName, coin)
            startActivity(intent)
        }
    }
}