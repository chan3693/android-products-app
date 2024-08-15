package com.example.session01_starter.retrofit

import com.example.products_sheungkit.models.Product
import com.example.products_sheungkit.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceholderInterface {
    @GET("/products/category/{category}")
    suspend fun getProduct(@Path("category") category: String): ProductResponse

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

}