package com.example.products_sheungkit.models

import java.io.Serializable

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val brand: String,
    val category: String,
    val thumbnail: String
) : Serializable

data class ProductResponse(
    val products: List<Product>
) : Serializable