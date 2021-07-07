package com.example.listapplication.view.ui.details

import com.example.listapplication.model.data.Item

sealed class ItemDetailsAction {

    object None : ItemDetailsAction()
    object Load : ItemDetailsAction()
    data class ItemLoaded(val item: Item) : ItemDetailsAction()
    data class Error(val error: Exception) : ItemDetailsAction()

}
