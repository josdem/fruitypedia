package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.view.View
import com.josdem.fruitypedia.state.ApplicationState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CategoryFragmentTest {
    @MockK
    private lateinit var view: View

    @MockK
    private lateinit var bundle: Bundle

    @InjectMockKs
    private var categoryFragment = CategoryFragment()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun shouldValidateCategoryFragment() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        categoryFragment.onViewCreated(view, bundle)
        assertEquals(categoryFragment, ApplicationState.getValue("categoryFragment"))
    }
}
