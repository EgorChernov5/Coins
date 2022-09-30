package com.example.coins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coins.apiCoins.CoinsItem

class CoinAdapter(private val coins: ArrayList<CoinsItem>, private val currency: CharSequence) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.li_coin_image)
        val nameView: TextView = view.findViewById(R.id.li_name)
        val shortNameView: TextView = view.findViewById(R.id.li_short_name)
        val priceView: TextView = view.findViewById(R.id.li_price)
        val percentView: TextView = view.findViewById(R.id.li_percent)
    }

    var onItemClick: ((CoinsItem) -> Unit)? = null

    // inflate a list_item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    // bind a list item to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCoins: CoinsItem = coins[position]

        Glide
            .with(holder.itemView.context)
            .load(itemCoins.image)
            .into(holder.imageView)
        holder.nameView.text = itemCoins.name
        holder.shortNameView.text = itemCoins.symbol.uppercase()
        holder.priceView.text = MyStrParser.getPrice(itemCoins.current_price, currency)
        holder.percentView.text = MyStrParser.getPercent(itemCoins.price_change_percentage_1h_in_currency)

        // set color for percent
        if (itemCoins.price_change_percentage_1h_in_currency >= 0) {
            holder.percentView.setTextColor(holder.itemView.context.getColor(R.color.green))
        } else {
            holder.percentView.setTextColor(holder.itemView.context.getColor(R.color.red))
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(itemCoins)
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}