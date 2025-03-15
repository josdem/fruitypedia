/*
Copyright 2025 Jose Morales contact@josdem.io

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 */

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
const val BEVERAGE = 85

class FruityServiceTest {
    private val fruityService: FruityService =
        RetrofitHelper.getInstance().create(FruityService::class.java)

    private suspend fun getCategories(): Response<List<Category>> {
        return fruityService.getCategories(LANGUAGE)
    }

    private suspend fun getBeverages(): Response<List<Beverage>> {
        return fruityService.getBeverages(CATEGORY)
    }

    private suspend fun getBeverage(): Response<Beverage> {
        return fruityService.getBeverage(BEVERAGE)
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

    @Test
    fun shouldGetBeverage() =
        runTest {
            val response = getBeverage()
            val beverage: Beverage? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals("Anti-constipation Smoothie", beverage?.name)
            assertEquals("https://storage.googleapis.com/jugoterapia/85.jpg", beverage?.image)
            assertEquals("1 Apple,1 Pear", beverage?.ingredients)
            assertEquals(
                "Start your day with apple and pear without peel getting juice as much as you can utilizing juice extractor" +
                    " or using just a blender. Apple juice contains sorbitol, " +
                    "natural sugar which helps to our body to avoid persistent constipation",
                beverage?.recipe,
            )
        }
}
