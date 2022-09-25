package com.example.coins

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CoinAdapter(context: Context, resource: Int, coins: List<Coin>) :
    ArrayAdapter<Coin>(context, resource, coins) {

    private val _inflater: LayoutInflater = LayoutInflater.from(context)
    private val _layout: Int = resource
    private val _coins: List<Coin> = coins

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val mConvertView: View

        if (convertView == null) {
            mConvertView = _inflater.inflate(_layout, parent, false)
            viewHolder = ViewHolder(mConvertView)
            mConvertView.tag = viewHolder
        } else {
            mConvertView = convertView
            viewHolder = mConvertView.tag as ViewHolder
        }

        val coin: Coin = _coins[position]

        viewHolder.imageView.setImageResource(coin.flagRes)
        viewHolder.nameView.text = coin.name
        viewHolder.priceView.text = coin.price.toString()

        return mConvertView
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.flag)
        val nameView: TextView = view.findViewById(R.id.name)
        val priceView: TextView = view.findViewById(R.id.price)
    }
}