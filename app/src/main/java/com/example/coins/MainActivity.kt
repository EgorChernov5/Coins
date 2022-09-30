package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coins.apiCoins.Coins
import com.example.coins.apiCoins.CoinsItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    private lateinit var coins: ArrayList<CoinsItem>
    private lateinit var currency: CharSequence
    private lateinit var chipsGroup: ChipGroup
    private lateinit var chip: Chip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Coin", "MainActivity: onCreate")

        // check currency
        val arguments = intent.extras
        if (arguments != null) {
            Log.d("Coin", "SplFirstActivity: Get chip")
            currency = arguments.getString("Currency").toString()
            chipsGroup = findViewById<ChipGroup>(R.id.ma_chips_group)
            chip = if (currency == "Eur") {
                chipsGroup.findViewById<Chip>(R.id.ma_eur)
            } else {
                chipsGroup.findViewById<Chip>(R.id.ma_usd)
            }
            chip.isChecked = true
        } else {
            throw Exception("Arguments == null")
        }

        chipsGroup.setOnCheckedStateChangeListener { chipGroup: ChipGroup,
                                                     checkedIds: MutableList<Int> ->
            Log.d("Coin", "MainActivity: Change chip")
            currency = chipGroup.findViewById<Chip>(checkedIds[0]).text
            val intent = Intent(this, SplFirstActivity::class.java)
            intent.putExtra("Currency", currency)
            startActivity(intent)
            finish()
        }

        // Set initial data()
        Log.d("Coin", "MainActivity: Get data")
        coins = arguments.getSerializable(Coins::class.java.simpleName) as ArrayList<CoinsItem>

        val recyclerView: RecyclerView = findViewById(R.id.ma_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val coinAdapter = CoinAdapter(coins, currency)
        recyclerView.adapter = coinAdapter

        coinAdapter.onItemClick = {
            Log.d("Coin", "MainActivity: Click on coin")
            // Выбираем элемент
            val coinId: String = it.id
            // Выводим результат
//            Toast.makeText(this, coinId, Toast.LENGTH_SHORT).show()
            // Переходим к CoinActivity через SplSecondActivity
            val intent = Intent(this, SplSecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.putExtra("coinId", coinId)
            startActivity(intent)
        }
    }
}