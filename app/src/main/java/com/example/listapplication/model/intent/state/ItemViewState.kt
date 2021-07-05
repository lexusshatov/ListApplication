package com.example.listapplication.model.intent.state

import com.example.listapplication.model.data.Item

sealed class ItemViewState: State {
    object Loading: ItemViewState()
    object NoFound: ItemViewState()
    class Loaded(val item: Item) : ItemViewState()
    class Error(val error: String): ItemViewState()
}