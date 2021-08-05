package com.example.test2.placeholder.Subcategories

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.CategoryAdapter
import com.example.test2.OnCategoryClickListener
import com.example.test2.R
import com.example.test2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_subcategory.*

class MoviesSubcategoryActivity : AppCompatActivity(), OnCategoryClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_subcategory)

        title = "Кинотеатры"

        init()

        /*binding.navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_entertaiment-> {
                    title=resources.getString(R.string.title_home)
                    loadFragment(EntertainmentFragment())
                    //return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_dashboard-> {
                    title=resources.getString(R.string.title_about_me)
                    loadFragment(AboutMeFragment())
                    //return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_store-> {
                    title=resources.getString(R.string.title_store)
                    loadFragment(StoreFragment())
                    //return@setOnNavigationItemSelectedListener true
                }

            }
            true
        }*/
    }

    private fun init() {

        fun getCategoriesList(): List<String> {
            return resources.getStringArray(R.array.movie_subcategory).toList()
        }

        fun getImagesList(): List<Int>{
            val arrImg: Array<Int> = arrayOf(
                R.drawable.raduga, R.drawable.grinn_film,
                R.drawable.rusich, R.drawable.cinema_star, R.drawable.cinema_park
            )
            return arrImg.toList()
        }

        val adapter: CategoryAdapter = CategoryAdapter(this@MoviesSubcategoryActivity,
            getCategoriesList(), getImagesList(), this@MoviesSubcategoryActivity)

        subcategory_recycler.layoutManager = LinearLayoutManager(this@MoviesSubcategoryActivity)
        subcategory_recycler.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    override fun onCategoryItemClicked(position: Int) {
        if (position == 0){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://belgorod.kinoafisha.info/cinema/8159215/"))
            startActivity(i)
        }
        if (position == 1){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://grinnfilm.ru/belgorod/"))
            startActivity(i)
        }
        if (position == 2){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://kinorusich.ru/"))
            startActivity(i)
        }
        if (position == 3){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.afisha.ru/belgorod/cinema/4873/"))
            startActivity(i)
        }
        if (position == 4){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://kinoteatr.ru/raspisanie-kinoteatrov/belgorod/belgorodskiy/"))
            startActivity(i)
        }
    }

    /*private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }*/
}