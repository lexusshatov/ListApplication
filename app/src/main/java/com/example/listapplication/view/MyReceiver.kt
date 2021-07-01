package com.example.listapplication.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.listapplication.R

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemId: Int? = context
            .getSharedPreferences(context.getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .getInt(context.getString(R.string.APP_PREFERENCES_ITEM_ID), -1)
        Log.d("Receiver", "Item value $itemId")

        val intent = Intent(context, ItemActivity::class.java)
        if (itemId != -1){
            intent.putExtra(EXTRA_NAME_ITEM, itemId)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}