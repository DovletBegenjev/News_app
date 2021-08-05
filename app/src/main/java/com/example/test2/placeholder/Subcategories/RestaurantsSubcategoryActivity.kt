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

class RestaurantsSubcategoryActivity : AppCompatActivity(), OnCategoryClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_subcategory)

        title = "Рестораны"

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
            return resources.getStringArray(R.array.restaurant_subcategory).toList()
        }

        fun getImagesList(): List<Int>{
            val arrImg: Array<Int> = arrayOf(
                R.drawable.gallery, R.drawable.eat_today,
                R.drawable.korchma, R.drawable.mamma_mia, R.drawable.mesto_grill,
            )
            return arrImg.toList()
        }

        val adapter: CategoryAdapter = CategoryAdapter(this@RestaurantsSubcategoryActivity,
            getCategoriesList(), getImagesList(), this@RestaurantsSubcategoryActivity)

        subcategory_recycler.layoutManager = LinearLayoutManager(this@RestaurantsSubcategoryActivity)
        subcategory_recycler.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    override fun onCategoryItemClicked(position: Int) {
        if (position == 0){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://rest-gallery.ru/menu/"))
            startActivity(i)
        }
        if (position == 1){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://bel.allcafe.ru/catalog/est-today/"))
            startActivity(i)
        }
        if (position == 2){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://resto31.ru/page/2/"))
            startActivity(i)
        }
        if (position == 3){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mamma.mia.belgorod/?hl=ru"))
            startActivity(i)
        }
        if (position == 4){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mesto_grill"))
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