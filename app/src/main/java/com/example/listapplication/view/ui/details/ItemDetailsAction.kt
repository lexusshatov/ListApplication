package com.natife.example.mviexample.ui.details

import com.natife.example.mviexample.data.model.Item

sealed class ItemDetailsAction {

    object None : ItemDetailsAction()
    object Load : ItemDetailsAction()
    data class ItemLoaded(val item: Item) : ItemDetailsAction()
    data class Error(val error: Exception) : ItemDetailsAction()

}
