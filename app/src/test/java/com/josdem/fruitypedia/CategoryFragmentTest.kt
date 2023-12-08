package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.view.View
import com.josdem.fruitypedia.state.ApplicationState
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryFragmentTest {

    @MockK
    private lateinit var view: View

    @MockK
    private lateinit var bundle: Bundle

    @Test
    fun shouldValidateCategoryFragment() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        val categoryFragment = CategoryFragment()
        categoryFragment.onViewCreated(view, bundle)
        assertEquals(categoryFragment, ApplicationState.getValue("categoryFragment"))
    }

}