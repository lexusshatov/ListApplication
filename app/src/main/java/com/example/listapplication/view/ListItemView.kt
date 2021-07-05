package com.example.listapplication.view

import com.example.listapplication.model.data.Item

interface ListItemView {
    fun showToast(message: String)
    fun showItems(items: List<Item>)
    fun startBackground()
    fun startItemActivity(item: Item)
}