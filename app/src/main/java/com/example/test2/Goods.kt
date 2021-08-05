package com.example.test2

class Goods {
    private val productName: String
    private val imageLink: String
    private val price: String

    constructor(_productName: String, _imageLink: String, _price: String){
        productName = _productName
        imageLink = _imageLink
        price = _price
    }

    fun getProductName(): String{
        return productName
    }

    fun getImageLink(): String{
        return imageLink
    }

    fun getPrice(): String{
        return price
    }
}