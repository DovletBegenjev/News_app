package com.example.test2

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*

class CategoryAdapter(var context: Context, private val names: List<String>, private val images: List<Int>,
                      private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var categoryName: TextView? = null
        var categoryImage: ImageView? = null

        init {
            categoryName = itemView.findViewById(R.id.category_name)
            categoryImage = itemView.findViewById(R.id.category_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.category_name.text = names[position]

        holder.itemView.category_image.setImageResource(images[position])

        holder.itemView.setOnClickListener{
            onCategoryClickListener.onCategoryItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }
}