package com.josdem.fruitypedia

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.josdem.fruitypedia.state.ApplicationState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class RecipeFragmentTest {
    @MockK
    private lateinit var view: View

    @MockK
    private lateinit var bundle: Bundle

    @InjectMockKs
    private var recipeFragment = RecipeFragment()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun shouldValidateRecipeFragment() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        ApplicationState.storeValue("currentBeverage", 0)

        recipeFragment.onViewCreated(view, bundle)

        verify(exactly = 0) { view.findViewById<TextView>(R.id.listViewBeverages) }
    }
}
