package com.example.listapplication.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(@PrimaryKey val id: Int) {
    val name = "$id item"
    val description = "description of $id"
    override fun toString(): String {
        return name
    }
}
