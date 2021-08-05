package com.example.test2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private val onNewsClickListener: OnCategoryClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val listNews = mutableListOf<News>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.category_name)
        private val image: ImageView = itemView.findViewById(R.id.category_image)
        private var articleInfo = ""

        fun bind(news: News) {
            title.text = news.getTitle()
            Picasso.get().load(news.getImageLink()).into(image)
            articleInfo = news.getArticleLink()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNews[position])
        holder.itemView.setOnClickListener{
            onNewsClickListener.onCategoryItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    fun set(list: MutableList<News>){
        this.listNews.clear()
        this.listNews.addAll(list)
        notifyDataSetChanged()
    }
}