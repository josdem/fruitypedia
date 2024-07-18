package com.josdem.fruitypedia

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.LayoutAssertions.noEllipsizedText
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josdem.fruitypedia.state.ApplicationState
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val HEALING_CATEGORY = 5

@RunWith(AndroidJUnit4::class)
class BeverageFragmentTest {
    @Before
    fun init() {
        ApplicationState.storeValue("currentCategory", HEALING_CATEGORY)
    }

    @Test
    fun shouldValidateBeverageFragment() {
        val scenario =
            launchFragmentInContainer<BeverageFragment>(
                initialState = Lifecycle.State.INITIALIZED,
            )
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.listViewBeverages)).check(noOverlaps())
        onView(withId(R.id.listViewBeverages)).check(noEllipsizedText())

        scenario.onFragment { fragment ->
            assertTrue(fragment.view is ConstraintLayout)
        }
    }
}
