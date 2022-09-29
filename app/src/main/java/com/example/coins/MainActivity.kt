package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private val coins: MutableList<Coin> = mutableListOf()
    private var coinAdapter: CoinAdapter? = null
    var currency: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate")

        val chipsGroup = findViewById<ChipGroup>(R.id.ma_chips_group)
        val chip = chipsGroup.findViewById<Chip>(R.id.ma_usd)
        chip.isChecked = true
        currency = chip.text

        chipsGroup.setOnCheckedChangeListener { chipGroup: ChipGroup, checkedId: Int ->
            Log.d("MainActivity", "Change chip")
            currency = chipGroup.findViewById<Chip>(checkedId)?.text
            Toast.makeText(chipGroup.context, currency ?: "No Choice", Toast.LENGTH_SHORT).show()
        }

//        test
        setInitialData()

        listView = findViewById<ListView>(R.id.ma_coins_list)
        coinAdapter = CoinAdapter(this, R.layout.list_item, coins)
        listView?.adapter = coinAdapter
        listView?.setOnItemClickListener { parent: AdapterView<*>?, view: View?,
                                           position: Int, id: Long ->
            Log.d("MainActivity", "Click on coin")
            // Выбираем элемент, преобразуем к String
            val selectedCoin = parent?.getItemAtPosition(position) as Coin
            // Выводим результат
            Toast.makeText(this, selectedCoin.toString(), Toast.LENGTH_SHORT).show()
            // Переходим к CoinActivity через SplSecondActivity
            val intent = Intent(this, SplSecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.putExtra(selectedCoin::class.java.simpleName, selectedCoin)
            startActivity(intent)
        }
    }

    //    test
    private fun setInitialData(){
        coins.add(Coin ("Bitcoin", "BTC",  "$19058.57", "1.7%", R.drawable.bitcoin))
        coins.add(Coin ("Bnb", "BNB", "$275.60", "0.4%", R.drawable.bnb))
        coins.add(Coin ("Solana","SOL", "$33.15","0", R.drawable.solana))
        coins.add(Coin ("Ethereum", "ETH", "$1311.50", "1.6%", R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", "BTC",  "$19058.57", "1.7%", R.drawable.bitcoin))
        coins.add(Coin ("Bnb", "BNB", "$275.60", "0.4%", R.drawable.bnb))
        coins.add(Coin ("Solana","SOL", "$33.15","0", R.drawable.solana))
        coins.add(Coin ("Ethereum", "ETH", "$1311.50", "1.6%", R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", "BTC",  "$19058.57", "1.7%", R.drawable.bitcoin))
        coins.add(Coin ("Bnb", "BNB", "$275.60", "0.4%", R.drawable.bnb))
        coins.add(Coin ("Solana","SOL", "$33.15","0", R.drawable.solana))
        coins.add(Coin ("Ethereum", "ETH", "$1311.50", "1.6%", R.drawable.ethereum))
    }
}