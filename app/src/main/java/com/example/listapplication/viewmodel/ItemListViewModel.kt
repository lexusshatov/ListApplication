package com.example.listapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapplication.model.database.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemListViewModel(private val itemRepository: ItemRepository): ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val data = itemRepository.data

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                itemRepository.refresh()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}