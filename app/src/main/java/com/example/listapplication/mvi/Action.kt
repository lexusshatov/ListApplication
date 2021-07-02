package com.example.listapplication.mvi

import android.content.Intent

sealed class Action {
    data class ShowError(val error: String) : Action()
    data class StartItemActivity(val intent: Intent) : Action()
}
