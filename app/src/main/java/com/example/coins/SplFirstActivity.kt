package com.example.coins

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coins.apiCoins.Coins
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplFirstActivity : AppCompatActivity() {
    private lateinit var currency: CharSequence
    private lateinit var chipsGroup: ChipGroup
    private lateinit var chip: Chip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spl_first)

        Log.d("Coin", "SplFirstActivity: onCreate")

        // check currency
        val arguments = intent.extras
        if (arguments == null) {
            Log.d("Coin", "SplFirstActivity: Chip is usd")
            chipsGroup = findViewById<ChipGroup>(R.id.sfa_chips_group)
            chip = chipsGroup.findViewById<Chip>(R.id.sfa_usd)
            chip.isChecked = true
            currency = chip.text
        } else {
            Log.d("Coin", "SplFirstActivity: Change chip")
            currency = arguments.getString("Currency").toString()
            chipsGroup = findViewById<ChipGroup>(R.id.sfa_chips_group)
            chip = if (currency == "Eur") {
                chipsGroup.findViewById<Chip>(R.id.sfa_eur)
            } else {
                chipsGroup.findViewById<Chip>(R.id.sfa_usd)
            }
            chip.isChecked = true
        }

        chipsGroup.setOnCheckedChangeListener { chipGroup: ChipGroup, checkedId: Int ->
            Log.d("Coin", "ErrorFirstActivity: Change chip")
            currency = chipGroup.findViewById<Chip>(checkedId).text
            Toast.makeText(chipGroup.context, currency, Toast.LENGTH_SHORT).show()
        }

        Handler().postDelayed({
            val apiInterface = ApiInterface.create().getCoins(
                currency.toString().lowercase(),
                10,
                1,
                false,
                "1h")

            apiInterface.enqueue( object : Callback<Coins> {
                override fun onResponse(call: Call<Coins>, response: Response<Coins>) {
                    Log.d("Coin", "SplFirstActivity: Loading coins data list")
                    try {
                        if (response.body() == null) throw Exception("Empty response!")
                        val coins: Coins? = response.body()
                        val intent = Intent(this@SplFirstActivity, MainActivity::class.java)
                        intent.putExtra(Coins::class.java.simpleName, coins)
                        intent.putExtra("Currency", currency)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println(e.message)
                        val intent = Intent(this@SplFirstActivity, ErrorFirstActivity::class.java)
                        intent.putExtra("Currency", currency)
                        startActivity(intent)
                    } finally {
                        finish()
                    }
                }

                override fun onFailure(call: Call<Coins>, t: Throwable) {
                    Log.d("Coin", "SplFirstActivity: Request error!")
                    val intent = Intent(this@SplFirstActivity, ErrorFirstActivity::class.java)
                    intent.putExtra("Currency", currency)
                    startActivity(intent)
                    finish()
                }
            })
        },1000)
    }
}