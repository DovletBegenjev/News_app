package com.example.test2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AboutMeFragment : Fragment() {

    private lateinit var score: SharedPreferences
    private val APP_PREFERENCES: String = "mySettings"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onStart() {
        super.onStart()
        val user = Users(R.drawable.arthur_morgan,
            "Довлет Бегенджев",
            "Про меня мало кто что-то знает. И это меня радует.",
            "09.10.1999",
            "Белгород",
            "студент",
            "+7 999 111-22-33",
            "AAA")
        val image: ImageView = requireView().findViewById(R.id.imageView)
        val name: TextView = requireView().findViewById(R.id.name)
        val me: TextView = requireView().findViewById(R.id.aboutMe)
        val date: TextView = requireView().findViewById(R.id.dateBrth)
        val city: TextView = requireView().findViewById(R.id.city)
        val work: TextView = requireView().findViewById(R.id.work)
        val phone: TextView = requireView().findViewById(R.id.phone)
        val social: ImageView = requireView().findViewById(R.id.socialMedia)

        image.setImageResource(user.getImage())
        name.text = user.getName()
        me.text = user.getAboutMe()
        date.text = user.getDateBrth()
        city.text = user.getCity()
        work.text = user.getWork()
        phone.text = user.getPhone()
        social.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Cacoon_Vigvam"))
            startActivity(i)
        }

        score = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val scoreValue: TextView = requireView().findViewById(R.id.score)
        scoreValue.text = score.getInt("score", 0).toString()
    }

}