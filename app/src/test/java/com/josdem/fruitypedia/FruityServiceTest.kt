package com.josdem.fruitypedia

import com.josdem.fruitypedia.service.FruityService
import com.josdem.fruitypedia.service.RetrofitHelper
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class FruityServiceTest {
    private val fruityService: FruityService =
        RetrofitHelper.getInstance().create(FruityService::class.java)

    @Test
    fun shouldGetCategories() {
        assertNotNull(fruityService)
    }
}
