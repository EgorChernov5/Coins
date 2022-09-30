package com.example.coins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CoinAdapter(private val coins: ArrayList<CoinsItem>) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.li_coin_image)
        val nameView: TextView = view.findViewById(R.id.li_name)
        val shortNameView: TextView = view.findViewById(R.id.li_short_name)
        val priceView: TextView = view.findViewById(R.id.li_price)
        val percentView: TextView = view.findViewById(R.id.li_percent)
    }

    var onItemClick: ((CoinsItem) -> Unit)? = null

//    inflate a list_item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

//    bind a list item to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCoins: CoinsItem = coins[position]

        Glide
            .with(holder.itemView.context)
            .load(itemCoins.image)
            .into(holder.imageView)
        holder.shortNameView.text = itemCoins.symbol.uppercase()
        holder.priceView.text = itemCoins.current_price.toString()
        holder.percentView.text = itemCoins.price_change_percentage_1h_in_currency.toString()

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(itemCoins)
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}