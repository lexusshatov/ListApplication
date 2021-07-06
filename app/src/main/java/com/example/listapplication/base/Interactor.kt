package com.natife.example.mviexample.base

interface Interactor<State, Action> {

    suspend operator fun invoke(state: State, action: Action): Action

    fun canHandle(action: Action): Boolean

}