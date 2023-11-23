package com.josdem.fruitypedia.model

data class Beverage(
    val id: Int,
    val name: String,
    val ingredients: String,
    val recipe: String,
    val image: String,
) {
    override fun toString(): String = name
}
