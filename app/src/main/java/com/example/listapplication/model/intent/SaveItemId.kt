package com.example.listapplication.model.intent

import android.content.Context
import com.example.listapplication.view.activity.PREFERENCES_ITEM
import com.example.listapplication.view.activity.PREFERENCES_NAME

class SaveItemId(private val context: Context) {

    operator fun invoke(itemId: Int){
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(PREFERENCES_ITEM, itemId)
            .apply()
    }

}