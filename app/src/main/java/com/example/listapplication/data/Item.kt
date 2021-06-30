package com.example.listapplication.data

import java.io.Serializable

data class Item(val id: Int): Serializable {
    val name = "$id item"
    val description = "description of $id"
    override fun toString(): String {
        return name
    }
}
