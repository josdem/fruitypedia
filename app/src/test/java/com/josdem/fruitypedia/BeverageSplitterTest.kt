package com.josdem.fruitypedia

import com.josdem.fruitypedia.util.BeverageSplitter
import org.junit.Assert.assertEquals
import org.junit.Test

class BeverageSplitterTest {

    @Test
    fun shouldSplitIngredients() {
        val ingredients = "5 Blueberries,1 Apple,2 Oranges,Honey to taste"
        val result = BeverageSplitter.split(ingredients)
        assertEquals("5 Blueberries" + "\n" + "1 Apple" + "\n" +"2 Oranges" + "\n" + "Honey to taste" + "\n", result)
    }
}
