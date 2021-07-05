package com.example.listapplication.intent

import com.example.listapplication.intent.ListItemViewAction
import com.example.listapplication.intent.ListItemViewEvent
import com.example.listapplication.intent.ListItemViewState

interface ListItemHandler {
    fun render(state: ListItemViewState)
    fun obtainEvent(event: ListItemViewEvent)
    fun makeAction(action: ListItemViewAction)
}