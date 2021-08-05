package com.example.test2.placeholder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class NewsFragment : Fragment(), OnCategoryClickListener {
    private val url = "https://www.go31.ru/news"
    private val listNews = mutableListOf<News>()
    private lateinit var adapter: NewsAdapter
    private var articleLink = ""
    private var articleLinksArray = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = NewsAdapter(this)
        val llm = LinearLayoutManager(this.context)

        news_recycler.layoutManager = llm
        news_recycler.adapter = adapter

        adapter.notifyDataSetChanged()

        GlobalScope.launch {
            getData()
        }
    }

    private fun getData() {
        val document = Jsoup.connect(url).get()
        val element = document.select("div.c-news-block")

        try {
            for (i in 0 until element.size) {
                val title = element.select("div.c-news-block__head")
                    .select("a.c-news-block__title")
                    .eq(i)
                    .text()

                val image = element.select("a.c-news-block__image")
                    .eq(i)
                    .attr("data-src")

                articleLink = element.select("div.c-news-block__head")
                    .select("a.c-news-block__title")
                    .eq(i)
                    .attr("href")

                listNews.add(News(title, image, articleLink))
                articleLinksArray.add(articleLink)
            }
            GlobalScope.launch(Dispatchers.Main) {
                adapter.set(listNews)
            }
        } catch (e: IOException){
            Toast.makeText(requireContext(), "Error: $e", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCategoryItemClicked(position: Int) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(articleLinksArray[position]))
        startActivity(i)
    }
}