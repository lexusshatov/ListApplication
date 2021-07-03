package com.example.listapplication.model

interface ListItemView {
    fun render(state: ListItemViewState)
    fun obtainEvent(event: ListItemViewEvent)
    fun makeAction(action: ListItemViewAction)
}