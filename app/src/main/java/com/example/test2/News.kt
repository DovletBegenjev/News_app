package com.example.test2

class News{
    private val title: String
    private val imageLink: String
    private val articleLink: String

    constructor(_title: String, _imageLink: String, _articleLink: String){
        title = _title
        imageLink = _imageLink
        articleLink = _articleLink
    }

    fun getTitle(): String{
        return title
    }

    fun getImageLink(): String{
        return imageLink
    }

    fun getArticleLink(): String{
        return articleLink
    }
}