package com.josdem.fruitypedia.service

import com.josdem.fruitypedia.model.Category
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface FruityService {

    @GET("/categories/en")
    suspend fun getCategories(): Response<List<Category>>
}