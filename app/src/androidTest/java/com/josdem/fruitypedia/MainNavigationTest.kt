package com.josdem.fruitypedia

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.josdem.fruitypedia.model.Category
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainNavigationTest {
    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainNavigationTest() {
        val healingTextView =
            onData(allOf(`is`(instanceOf(Category::class.java)), `is`(Category(5, "Healing"))))
        healingTextView.perform(click())

        val beverages = onView(allOf(withId(R.id.listViewBeverages)))
        beverages.check(matches(isDisplayed()))

        val beverage =
            onData(
                allOf(
                    `is`(instanceOf(Map::class.java)),
                    hasEntry(
                        equalTo("NAME"),
                        `is`("Anti-constipation Smoothie"),
                    ),
                ),
            )
        beverage.check(matches(isCompletelyDisplayed()))
    }
}
