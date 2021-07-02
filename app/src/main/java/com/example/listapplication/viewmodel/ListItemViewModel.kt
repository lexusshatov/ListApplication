package com.example.listapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapplication.model.database.repository.ItemRepository
import kotlinx.coroutines.launch

class ListItemViewModel(private val itemRepository: ItemRepository): ViewModel() {
    val data = itemRepository.data

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            try {
                itemRepository.refresh()
            } catch (e: Exception) {
               e.printStackTrace()
            }
        }
    }
}