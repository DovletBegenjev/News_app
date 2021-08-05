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

class TheatresSubcategoryActivity : AppCompatActivity(), OnCategoryClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_subcategory)

        title = "Театры"

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
            return resources.getStringArray(R.array.theatre_subcategory).toList()
        }

        fun getImagesList(): List<Int>{
            val arrImg: Array<Int> = arrayOf(
                R.drawable.dolls_theatre, R.drawable.drama_theatre,
                R.drawable.rukavichka, R.drawable.new_stage_2
            )
            return arrImg.toList()
        }

        val adapter: CategoryAdapter = CategoryAdapter(this@TheatresSubcategoryActivity,
            getCategoriesList(), getImagesList(), this@TheatresSubcategoryActivity)

        subcategory_recycler.layoutManager = LinearLayoutManager(this@TheatresSubcategoryActivity)
        subcategory_recycler.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    override fun onCategoryItemClicked(position: Int) {
        if (position == 0){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://bgtk.org/"))
            startActivity(i)
        }
        if (position == 1){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://beltheatre.ru/"))
            startActivity(i)
        }
        if (position == 2){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.xn----8sbafc0bkbd9ctp0d.xn--p1ai/"))
            startActivity(i)
        }
        if (position == 3){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/newstage_2"))
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