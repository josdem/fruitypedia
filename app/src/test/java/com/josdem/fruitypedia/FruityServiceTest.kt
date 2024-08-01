package com.josdem.fruitypedia

import com.josdem.fruitypedia.model.Beverage
import com.josdem.fruitypedia.model.Category
import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

const val LANGUAGE = "en"
const val CATEGORY = 5

class FruityServiceTest {
    private val fruityService: FruityService =
        RetrofitHelper.getInstance().create(FruityService::class.java)

    private suspend fun getCategories(): Response<List<Category>> {
        return fruityService.getCategories(LANGUAGE)
    }

    private suspend fun getBeverages(): Response<List<Beverage>> {
        return fruityService.getBeverages(CATEGORY)
    }

    @Test
    fun shouldGetCategories() =
        runTest {
            val response = getCategories()
            val categories: List<Category>? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals(4, categories?.size)
        }

    @Test
    fun shouldGetBeverages() =
        runTest {
            val response = getBeverages()
            val beverages: List<Beverage>? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals(14, beverages?.size)
        }
}
