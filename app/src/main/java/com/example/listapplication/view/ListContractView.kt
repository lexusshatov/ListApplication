package com.example.listapplication.view

import com.example.listapplication.model.data.Item

interface ListContractView {
    fun showList(items: List<Item>)
    fun startBackground()
    fun saveItem(item: Item)
    fun startItemActivity(item: Item)
}