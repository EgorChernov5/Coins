package com.example.coins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val chipsGroup = findViewById<ChipGroup>(R.id.chips_group)
        val chip = chipsGroup.findViewById<Chip>(R.id.usd)
        chip.isChecked = true
        currency = chip.text

        chipsGroup.setOnCheckedChangeListener { chipGroup: ChipGroup, checkedId: Int ->
            currency = chipGroup.findViewById<Chip>(checkedId)?.text
            Toast.makeText(chipGroup.context, currency ?: "No Choice", Toast.LENGTH_SHORT).show()
        }

//        test
        setInitialData()

        listView = findViewById<ListView>(R.id.coins_list)
        coinAdapter = CoinAdapter(this, R.layout.list_item, coins)
        listView?.adapter = coinAdapter
        listView?.setOnItemClickListener { parent: AdapterView<*>?, view: View?,
                                           position: Int, id: Long ->
            // Выбираем элемент, преобразуем к String
            val selectedCoin = parent?.getItemAtPosition(position) as Coin
            // Выводим результат
            Toast.makeText(this, selectedCoin.toString(), Toast.LENGTH_SHORT).show()
        }
    }

//    test
    private fun setInitialData(){
        coins.add(Coin ("Bitcoin", 19058.57f, R.drawable.bitcoin))
        coins.add(Coin ("Bnb", 275.60f, R.drawable.bnb))
        coins.add(Coin ("Solana", 33.15f, R.drawable.solana))
        coins.add(Coin ("Ethereum", 1311.50f, R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", 19058.57f, R.drawable.bitcoin))
        coins.add(Coin ("Bnb", 275.60f, R.drawable.bnb))
        coins.add(Coin ("Solana", 33.15f, R.drawable.solana))
        coins.add(Coin ("Ethereum", 1311.50f, R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", 19058.57f, R.drawable.bitcoin))
        coins.add(Coin ("Bnb", 275.60f, R.drawable.bnb))
        coins.add(Coin ("Solana", 33.15f, R.drawable.solana))
        coins.add(Coin ("Ethereum", 1311.50f, R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", 19058.57f, R.drawable.bitcoin))
        coins.add(Coin ("Bnb", 275.60f, R.drawable.bnb))
        coins.add(Coin ("Solana", 33.15f, R.drawable.solana))
        coins.add(Coin ("Ethereum", 1311.50f, R.drawable.ethereum))

    }
}