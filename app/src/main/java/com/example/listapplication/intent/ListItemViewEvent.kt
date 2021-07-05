package com.example.listapplication.intent

import com.example.listapplication.model.data.Item

sealed class ListItemViewEvent {
    object ViewStarted: ListItemViewEvent()
    class ClickOnItem(val item: Item) : ListItemViewEvent()
}
