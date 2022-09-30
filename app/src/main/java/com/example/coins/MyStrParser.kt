package com.example.coins

class MyStrParser() {
    companion object {
        fun getPrice(current_price: Double, currency: CharSequence): String {
            val sing: String = if (currency == "Usd") "$ " else "€ "
            val str = String.format("%.2f", current_price)
            return sing + str
        }

        fun getPercent(percent: Double): String {
            val sing: String = if (percent >= 0) "+" else ""
            val str = String.format("%.2f", percent)
            return "${sing}${str}%"
        }
    }
}