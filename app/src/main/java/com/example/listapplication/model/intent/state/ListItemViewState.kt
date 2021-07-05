package com.example.listapplication.model.intent.state

import com.example.listapplication.model.data.Item

sealed class ListItemViewState: State {
    object Loading: ListItemViewState()
    object NoItems: ListItemViewState()
    class Loaded(val items: List<Item>) : ListItemViewState()
    class Error(val error: String): ListItemViewState()
}

