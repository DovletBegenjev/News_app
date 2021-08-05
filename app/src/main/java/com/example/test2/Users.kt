package com.example.test2

class Users {
    private var image: Int
    private var name: String
    private var aboutMe: String
    private var dateBrth: String
    private var city: String
    private var work: String
    private var phone: String
    private var socialMedia: String

    constructor(_image: Int, _name: String, _aboutMe: String, _dateBrth: String, _city: String, _work: String, _phone: String, _socialMedia: String){
        image = _image
        name = _name
        aboutMe = _aboutMe
        dateBrth = _dateBrth
        city = _city
        work = _work
        phone = _phone
        socialMedia = _socialMedia
    }

    fun getImage(): Int{
        return image
    }

    fun getName(): String{
        return name
    }

    fun getAboutMe(): String{
        return aboutMe
    }

    fun getDateBrth(): String{
        return dateBrth
    }

    fun getCity(): String{
        return city
    }

    fun getWork(): String{
        return work
    }

    fun getPhone(): String{
        return phone
    }

    fun getSocialMedia(): String{
        return socialMedia
    }
}