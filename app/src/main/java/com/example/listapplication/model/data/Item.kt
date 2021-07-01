package com.example.listapplication.model.data

data class Item(val id: Int, val name: String, val description: String) {

    override fun toString(): String {
        return name
    }
}
