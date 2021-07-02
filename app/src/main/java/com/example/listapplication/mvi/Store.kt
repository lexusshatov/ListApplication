package com.example.listapplication.mvi

import android.content.Intent
import com.github.c5fr7q.jungle.Store
import com.github.c5fr7q.jungle.command.Command
import com.github.c5fr7q.jungle.command.CommandResult
import io.reactivex.Scheduler

class Store (
    foregroundScheduler: Scheduler,
    backgroundScheduler: Scheduler,
    itemMiddleware: ItemMiddleware
) : Store<Event, State, Action>(
    foregroundScheduler = foregroundScheduler,
    backgroundScheduler = backgroundScheduler
) {
    override val middlewares = listOf(itemMiddleware)
    override val initialState = State()

    override fun convertEvent(event: Event) =
        when (event) {
        is Event.Load -> ItemMiddleware.Input
        is Event.ClickItem -> ItemMiddleware.Input
    }

    override fun produceAction(command: Command) =
        when (command) {
            is ProduceActionCommand.Error ->
                Action.ShowError(command.error)
            is ProduceActionCommand.StartItemActivity ->
                Action.StartItemActivity(command.intent)
            else -> null
        }

    override fun produceCommand(commandResult: CommandResult) =
        when (commandResult) {
            is ItemMiddleware.Output.Failed ->
                ProduceActionCommand.Error(commandResult.error)
            else -> null
        }

    override fun reduceCommandResult(state: State, commandResult: CommandResult) = when (commandResult) {
        is ItemMiddleware.Output.Loading ->
            state.copy(isLoading = true)
        is ItemMiddleware.Output.Loaded ->
            state.copy(isLoading = false, items = commandResult.items)
        is ItemMiddleware.Output.Failed ->
            state.copy(isLoading = false)
        else -> state
    }

    sealed class ProduceActionCommand : Command {
        data class Error(val error: String) : ProduceActionCommand()
        data class StartItemActivity(val intent: Intent) : ProduceActionCommand()
    }
}