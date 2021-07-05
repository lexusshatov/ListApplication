package com.example.listapplication.model.intent.render

import com.example.listapplication.model.data.Item
import com.example.listapplication.model.intent.state.ListItemViewState

class ListViewStateRender {

    operator fun invoke(loading: Boolean, error: String?, items: List<Item>?): ListItemViewState {
        if (!error.isNullOrEmpty()) return ListItemViewState.Error(error)
        if (loading) return ListItemViewState.Loading
        return if (items.isNullOrEmpty()) ListItemViewState.NoItems
        else ListItemViewState.Loaded(items)
    }

}