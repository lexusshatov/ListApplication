package com.example.listapplication.mvi

sealed class Event {
    object Load : Event()
    object ClickItem : Event()
}
