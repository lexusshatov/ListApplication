package com.example.listapplication.model.database.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listapplication.model.database.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun findAll(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(items: List<Item>)
}