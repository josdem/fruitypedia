package com.josdem.fruitypedia

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.LayoutAssertions.noEllipsizedText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josdem.fruitypedia.state.ApplicationState
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeverageFragmentTest {

    @Before
    fun init() {
        ApplicationState.storeValue("currentCategory", 5)
    }

    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<BeverageFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.listViewBeverages)).check(noEllipsizedText())
    }
}