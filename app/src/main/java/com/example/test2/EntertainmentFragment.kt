package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.test2.placeholder.Subcategories.*
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_entertainment.*

class EntertainmentFragment : Fragment(), OnCategoryClickListener {

    private lateinit var viewPager: ViewPager2
    var handler: Handler = Handler()
    var runnable: Runnable = Runnable {  }
    private lateinit var images: List<Int>
    private var curImg = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        fun getCategoriesList(): List<String> {
            return this.resources.getStringArray(R.array.category_names).toList()
        }

        fun getImagesList(): List<Int>{
            val arrImg: Array<Int> = arrayOf(R.drawable.ic_baseline_shopping_basket_100,
                R.drawable.ic_baseline_local_movies_100, R.drawable.ic_baseline_tag_faces_100,
                R.drawable.ic_baseline_restaurant_100, R.drawable.ic_baseline_local_bar_100)
            return arrImg.toList()
        }

        val adapter: CategoryAdapter = CategoryAdapter(requireContext(), getCategoriesList(), getImagesList(), this)

        category_recycler.layoutManager = LinearLayoutManager(this.context)
        category_recycler.adapter = adapter

        adapter.notifyDataSetChanged()

        // viewPager
         images = listOf(
            R.drawable.gallery,
            R.drawable.eat_today,
            R.drawable.korchma,
            R.drawable.mamma_mia,
            R.drawable.mesto_grill
        )

        val vpAdapter = ViewPagerAdapter(images)
        view_pager.adapter = vpAdapter

        // indicator dots tab
        TabLayoutMediator(tabDots, view_pager) { tab, position ->
            //Toast.makeText(requireContext(), "Position: $position", Toast.LENGTH_SHORT).show()
        }.attach()

        // to continue auto swipe from selected image
        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                curImg = position
            }
        })
    }

    override fun onCategoryItemClicked(position: Int) {
        if (position == 0) {
            val intent = Intent(requireContext(), MallsSubcategoryActivity::class.java)
            startActivity(intent)
        }
        if (position == 1) {
            val intent = Intent(requireContext(), MoviesSubcategoryActivity::class.java)
            startActivity(intent)
        }
        if (position == 2) {
            val intent = Intent(requireContext(), TheatresSubcategoryActivity::class.java)
            startActivity(intent)
        }
        if (position == 3) {
            val intent = Intent(requireContext(), RestaurantsSubcategoryActivity::class.java)
            startActivity(intent)
        }
        if (position == 4) {
            val intent = Intent(requireContext(), BarsSubcategoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        // viewPager auto swipe
        runnable = Runnable {
            if (curImg > images.size) curImg = 0
            view_pager.setCurrentItem(curImg, true)
            ++curImg

            handler.postDelayed(runnable, 3000)
        }
        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        --curImg
        handler.removeCallbacks(runnable)
    }
}