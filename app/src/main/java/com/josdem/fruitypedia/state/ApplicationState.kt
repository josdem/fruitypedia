package com.josdem.fruitypedia.state

object ApplicationState {
    private val memory = HashMap<String, Any>()

    fun storeValue(
        key: String,
        value: Any,
    )  {
        memory[key] = value
    }

    fun getValue(key: String): Any? {
        return memory[key]
    }
}
