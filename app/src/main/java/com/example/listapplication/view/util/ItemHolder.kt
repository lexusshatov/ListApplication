package com.natife.example.mviexample.util

import com.natife.example.mviexample.data.model.Item

object ItemHolder {

    val items by lazy {
        (0 until 20).map {
            Item(
                id = it,
                name = "name$it"
            )
        }
    }

    fun findItemById(itemId: Int) = items.first { it.id == itemId }

}