package com.example.listapplication.model

import com.example.listapplication.model.data.Item

sealed class ListItemViewState {
    object Loading: ListItemViewState()
    class Loaded(val items: List<Item>) : ListItemViewState()
    object NoItems: ListItemViewState()
    class Error(val error: String): ListItemViewState()
}

