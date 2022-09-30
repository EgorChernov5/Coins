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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val coins: MutableList<Coin> = mutableListOf()
    private lateinit var currency: CharSequence

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
            currency = chipGroup.findViewById<Chip>(checkedId).text
            Toast.makeText(chipGroup.context, currency ?: "No Choice", Toast.LENGTH_SHORT).show()
        }

//        test
        setInitialData()

        val recyclerView: RecyclerView = findViewById(R.id.ma_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val coinAdapter = CoinAdapter(coins)
        recyclerView.adapter = coinAdapter

        coinAdapter.onItemClick = {
            Log.d("MainActivity", "Click on coin")
            // Выбираем элемент
            val selectedCoin = it
            // Выводим результат
            Toast.makeText(this, selectedCoin.toString(), Toast.LENGTH_SHORT).show()
            // Переходим к CoinActivity через SplSecondActivity
            val intent = Intent(this, SplSecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.putExtra(selectedCoin::class.java.simpleName, selectedCoin)
            startActivity(intent)
        }

        val apiInterface = ApiInterface.create().getCoins()

//        put apiInterface in SplFirstActivity todo
        apiInterface.enqueue( object : Callback<List<Coin>> {
//            use when response is correct
            override fun onResponse(call: Call<List<Coin>>, response: Response<List<Coin>>) {
//                bind response to coinAdapter todo
//                if(response?.body() != null)
//                    coinAdapter.setMovieListItems(response.body()!!)
            }
//            use when response isn't correct
            override fun onFailure(call: Call<List<Coin>>, t: Throwable) {

            }
        })
    }

    //    test
    private fun setInitialData(){
        coins.add(Coin ("Bitcoin", "BTC",  "$19058.57", "1.7%", R.drawable.bitcoin1))
        coins.add(Coin ("Bnb", "BNB", "$275.60", "0.4%", R.drawable.bnb))
        coins.add(Coin ("Solana","SOL", "$33.15","0", R.drawable.solana))
        coins.add(Coin ("Ethereum", "ETH", "$1311.50", "1.6%", R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", "BTC",  "$19058.57", "1.7%", R.drawable.bitcoin1))
        coins.add(Coin ("Bnb", "BNB", "$275.60", "0.4%", R.drawable.bnb))
        coins.add(Coin ("Solana","SOL", "$33.15","0", R.drawable.solana))
        coins.add(Coin ("Ethereum", "ETH", "$1311.50", "1.6%", R.drawable.ethereum))
        coins.add(Coin ("Bitcoin", "BTC",  "$19058.57", "1.7%", R.drawable.bitcoin1))
        coins.add(Coin ("Bnb", "BNB", "$275.60", "0.4%", R.drawable.bnb))
        coins.add(Coin ("Solana","SOL", "$33.15","0", R.drawable.solana))
        coins.add(Coin ("Ethereum", "ETH", "$1311.50", "1.6%", R.drawable.ethereum))
    }
}