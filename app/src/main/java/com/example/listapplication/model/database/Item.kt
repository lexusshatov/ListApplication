package com.example.listapplication.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(@PrimaryKey val id: Int,
                val name: String,
                val description: String) {
    override fun toString(): String {
        return name
    }
}
