package com.example.test2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class StoreFragment : Fragment() {

    private val url = "https://sbermegamarket.ru/catalog/espandery-i-giroskopicheskie-trenazhery/"
    private val listGoods = mutableListOf<Goods>()
    private lateinit var adapter: GoodsAdapter

    private lateinit var score: SharedPreferences

    private var scoreValue: Int = 0

    private val APP_PREFERENCES: String = "mySettings"

    //private lateinit var editScore: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

        adapter = GoodsAdapter { p1 -> updateScore(p1) }
        val llm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        goods_recycler.layoutManager = llm
        goods_recycler.adapter = adapter

        GlobalScope.launch {
            getData()
        }

        val addButton = requireView().findViewById(R.id.add_button) as Button
        addButton.setOnClickListener {
            // add score
            val editor: SharedPreferences.Editor = score.edit()
            scoreValue = score.getInt("score", scoreValue)
            scoreValue += 1000
            editor.putInt("score", scoreValue)
            editor.apply()

            // show score
            updateScore(scoreValue)
        }
    }

    private fun getData() {
        val document = Jsoup.connect(url).get()
        val element = document.select("div.catalog-item")

        try {
            for (i in 0 until element.size) {
                val image = element.select("div.item-image")
                    .select("a")
                    .select("img")
                    .eq(i)
                    .attr("src")

                val productName = element.select("div.item-image")
                    .select("a")
                    .select("img")
                    .eq(i)
                    .attr("alt")

                val price = element.select("div.item-price")
                    .select("span")
                    .eq(i)
                    .text()

                listGoods.add(Goods(productName, image, price.split(" ₽".toRegex())[0]))
            }
            GlobalScope.launch(Dispatchers.Main) {
                adapter.set(listGoods)
            }
        } catch (e: IOException){
            Toast.makeText(requireContext(), "Error: $e", Toast.LENGTH_LONG).show()
        }
    }

    private fun init(){
        score = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        scoreValue = score.getInt("score", scoreValue)

        // show score
        updateScore(scoreValue)
    }

    private fun updateScore(updatedScoreValue: Int){
        val editScore: TextView = requireView().findViewById(R.id.user_score)
        editScore.text = score.getInt("score", updatedScoreValue).toString()
    }
}