package com.example.listapplication.view.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.listapplication.R
import com.example.listapplication.view.ItemActivity

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemId: Int? = context
            .getSharedPreferences(context.getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .getInt(context.getString(R.string.APP_PREFERENCES_ITEM_ID), -1)
        Log.d("Receiver", "Item id: $itemId")

        val intent = Intent(context, ItemActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}