package com.example.listapplication.model.task

import com.example.listapplication.model.data.Item

interface OnItemListLoadCallback {
    fun onLoadComplete(result: List<Item>)
}