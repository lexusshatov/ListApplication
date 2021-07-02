package com.example.listapplication.mvi

import com.example.listapplication.data.Item

data class State(val isLoading: Boolean = false, val items: List<Item> = emptyList())
