package com.josdem.fruitypedia.util

class BeverageSplitter {

    companion object {

        fun split(ingredients: String): String {
            val stringBuilder = StringBuilder()
            val token = ingredients.split(",")
            token.forEach {
                stringBuilder.append(it)
                stringBuilder.append("\n")
            }
            return stringBuilder.toString()
        }
    }
}