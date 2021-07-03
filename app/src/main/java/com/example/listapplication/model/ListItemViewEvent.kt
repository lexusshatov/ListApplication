package com.example.listapplication.model

import com.example.listapplication.model.data.Item

sealed class ListItemViewEvent {
    object ViewStarted: ListItemViewEvent()
    class ClickOnItem(val item: Item) : ListItemViewEvent()
}
