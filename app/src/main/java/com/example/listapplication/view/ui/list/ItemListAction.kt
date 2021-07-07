package com.example.listapplication.view.ui.list

import android.content.Context
import com.example.listapplication.model.data.Item

sealed class ItemListAction {

    object None : ItemListAction()
    object LoadItems : ItemListAction()
    data class ItemsLoaded(val items: List<Item>) : ItemListAction()
    data class Error(val error: Exception) : ItemListAction()
    data class SaveItem(val context: Context, val item: Item) : ItemListAction()
    object ItemSaved: ItemListAction()

    //For test purposes
    data class Test(val testData: String) : ItemListAction()

}
