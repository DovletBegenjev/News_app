package com.example.test2

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GoodsAdapter(private val refreshScore: (Int) -> Unit) : RecyclerView.Adapter<GoodsAdapter.ViewHolder>() {

    private val listGoods = mutableListOf<Goods>()

    class ViewHolder(itemView: View, val refreshScore: (Int) -> Unit) : RecyclerView.ViewHolder(itemView){
        private val image: ImageView = itemView.findViewById(R.id.product_image)
        private val productName: TextView = itemView.findViewById(R.id.product_name)
        private var price: TextView = itemView.findViewById(R.id.product_price)

        private val APP_PREFERENCES: String = "mySettings"
        private var score: SharedPreferences = itemView.context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        private var scoreValue: Int = 0

        fun bind(goods: Goods) {
            Picasso.get().load(goods.getImageLink()).into(image)
            productName.text = goods.getProductName()
            price.text = goods.getPrice()

            val buyButton = itemView.findViewById(R.id.buy_button) as Button
            buyButton.setOnClickListener{
                // subtract score
                val editor: SharedPreferences.Editor = score.edit()

                scoreValue = score.getInt("score", scoreValue)
                val parsedPrice = goods.getPrice().replace(" ", "").toInt()
                if (scoreValue > parsedPrice){
                    scoreValue -= parsedPrice
                } else {
                    Toast.makeText(itemView.context, "Недостаточно баллов!", Toast.LENGTH_SHORT).show()
                }

                editor.putInt("score", scoreValue)
                editor.apply()

                // show score
                refreshScore(scoreValue)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_product, parent, false)
        return ViewHolder(itemView, refreshScore)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGoods[position])
    }

    override fun getItemCount(): Int {
        return listGoods.size
    }

    fun set(list: MutableList<Goods>){
        this.listGoods.clear()
        this.listGoods.addAll(list)
        notifyDataSetChanged()
    }
}