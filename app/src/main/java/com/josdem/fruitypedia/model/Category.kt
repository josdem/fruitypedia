package com.josdem.fruitypedia.model

class Category {
    private var id: Int = 0
    private var name: String = ""

    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }
}