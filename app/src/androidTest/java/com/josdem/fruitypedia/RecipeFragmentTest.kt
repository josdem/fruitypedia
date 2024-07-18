package com.josdem.fruitypedia

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josdem.fruitypedia.state.ApplicationState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val SELECTED_BEVERAGE = 85

@RunWith(AndroidJUnit4::class)
class RecipeFragmentTest {
    @Before
    fun init() {
        ApplicationState.storeValue("currentBeverage", SELECTED_BEVERAGE)
    }

    @Test
    fun shouldValidateRecipeFragment() {
        val scenario =
            launchFragmentInContainer<RecipeFragment>(
                initialState = Lifecycle.State.INITIALIZED,
            )
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment { fragment ->
            assertEquals("name", fragment.resources.getResourceEntryName(R.id.name))
            assertEquals("image", fragment.resources.getResourceEntryName(R.id.image))
            assertEquals("ingredients", fragment.resources.getResourceEntryName(R.id.ingredients))
            assertEquals("recipe", fragment.resources.getResourceEntryName(R.id.recipe))
            assertTrue(fragment.view is ConstraintLayout)
        }
    }
}
