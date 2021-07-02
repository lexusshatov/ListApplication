package com.example.listapplication.mvi

import com.example.listapplication.data.Item
import com.github.c5fr7q.jungle.command.Command
import com.github.c5fr7q.jungle.command.CommandResult
import com.github.c5fr7q.jungle.command.Middleware
import io.reactivex.Observable
import io.reactivex.ObservableSource

class ItemMiddleware : Middleware<ItemMiddleware.Input>() {

    override fun transform(upstream: Observable<Input>): ObservableSource<CommandResult> {
        TODO("Not yet implemented")
    }

    object Input : Command

    sealed class Output : CommandResult {
        object Loading : Output()
        data class Loaded(val items: List<Item>) : Output()
        data class Failed(val error: String) : Output()
    }
}