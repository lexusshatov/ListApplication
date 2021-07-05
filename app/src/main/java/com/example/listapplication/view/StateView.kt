package com.example.listapplication.view

import com.example.listapplication.model.intent.state.State

interface StateView {
    fun render(state: State)
}