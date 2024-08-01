package com.josdem.fruitypedia

import com.josdem.fruitypedia.model.Category
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

const val LANGUAGE = "en"

class FruityServiceTest {
    private val fruityService: FruityService =
        RetrofitHelper.getInstance().create(FruityService::class.java)

    private suspend fun getCategories(): Response<List<Category>> {
        return fruityService.getCategories(LANGUAGE)
    }

    @Test
    fun shouldGetCategories() =
        runTest {
            val response = getCategories()
            val categories: List<Category>? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals(4, categories?.size)
        }
}
