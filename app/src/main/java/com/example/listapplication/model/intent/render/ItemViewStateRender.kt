package com.example.listapplication.model.intent.render

import com.example.listapplication.model.data.Item
import com.example.listapplication.model.intent.state.ItemViewState

class ItemViewStateRender {

    operator fun invoke(loading: Boolean, error: String?, item: Item?): ItemViewState {
        if (!error.isNullOrEmpty()) return ItemViewState.Error(error)
        if (loading) return ItemViewState.Loading
        return if (item == null) ItemViewState.NoFound
        else ItemViewState.Loaded(item)
    }

}