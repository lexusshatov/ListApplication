package com.natife.example.mviexample.ui.details

import com.natife.example.mviexample.data.model.Item

data class ItemDetailsState(
    val itemId: Int,
    val item: Item?
)
