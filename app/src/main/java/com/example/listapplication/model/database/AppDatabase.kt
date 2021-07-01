package com.example.listapplication.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listapplication.model.database.repository.ItemDao

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val itemDao: ItemDao
}