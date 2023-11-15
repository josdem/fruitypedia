package com.josdem.fruitypedia.service

import com.josdem.fruitypedia.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface FruityService {

    @GET("/categories/en")
    fun getCategories(): Call<List<Category>>
}