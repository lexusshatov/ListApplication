package com.example.listapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapplication.model.database.Item
import com.example.listapplication.model.database.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(private val itemRepository: ItemRepository): ViewModel() {
    val item = MutableLiveData<Item>()

    init {
        itemRepository.item?.let { requestItemById(it.id) }
    }

    private fun requestItemById(id: Int) {
        viewModelScope.launch {
            val item = id?.let { itemRepository.getItemById(it) }
            this@ItemViewModel.item.value = item
        }
    }

}