package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.example.test2.databinding.ActivityMainBinding
import com.example.test2.placeholder.NewsFragment
//import com.example.test2.placeholder.NewsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var clickedFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null){
            if (clickedFragment == R.id.navigation_news){
                //title=resources.getString(R.string.title_news)
                loadFragment(NewsFragment())
            }
            if (clickedFragment == R.id.navigation_entertainment){
                //title=resources.getString(R.string.title_entertainment)
                loadFragment(EntertainmentFragment())
            }
            if (clickedFragment == R.id.navigation_about_me){
                //title=resources.getString(R.string.title_about_me)
                loadFragment(AboutMeFragment())
            }
            if (clickedFragment == R.id.navigation_store){
                //title=resources.getString(R.string.title_store)
                loadFragment(StoreFragment())
            }
        } else{
            //title=resources.getString(R.string.title_news)
            loadFragment(NewsFragment())
        }

        binding.navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_news-> {
                    //title=resources.getString(R.string.title_news)
                    loadFragment(NewsFragment())
                    clickedFragment = R.id.navigation_news
                }

                R.id.navigation_entertainment-> {
                    //title=resources.getString(R.string.title_entertainment)
                    loadFragment(EntertainmentFragment())
                    clickedFragment = R.id.navigation_entertainment
                    //return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_about_me-> {
                    //title=resources.getString(R.string.title_about_me)
                    loadFragment(AboutMeFragment())
                    clickedFragment = R.id.navigation_about_me
                    //return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_store-> {
                    //title=resources.getString(R.string.title_store)
                    loadFragment(StoreFragment())
                    clickedFragment = R.id.navigation_store
                    //return@setOnNavigationItemSelectedListener true
                }

            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSaveInstanceState(
        outState: Bundle,
        outPersistentState: PersistableBundle
    ) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("clickedFragment", clickedFragment)
    }
}