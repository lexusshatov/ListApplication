package com.example.listapplication.model

import com.example.listapplication.model.data.Item

sealed class ListItemViewAction {
    class ShowItems(val items: List<Item>) : ListItemViewAction()
    class ShowItem(val item: Item) : ListItemViewAction()
}
