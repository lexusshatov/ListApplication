package com.example.listapplication.view

import com.example.listapplication.model.data.Item

interface ItemContractView {
    fun showItem(item: Item)
    fun backToStartActivity()
}