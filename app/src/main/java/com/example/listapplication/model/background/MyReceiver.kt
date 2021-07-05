package com.example.listapplication.model.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.listapplication.view.activity.EXTRA_ITEM_ID
import com.example.listapplication.view.activity.ItemActivity
import com.example.listapplication.view.activity.PREFERENCES_ITEM
import com.example.listapplication.view.activity.PREFERENCES_NAME

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemId: Int = context
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getInt(PREFERENCES_ITEM, -1)
        Log.d("Receiver", "Item value $itemId")

        val intentStartItemActivity = Intent(context, ItemActivity::class.java)
        intentStartItemActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intentStartItemActivity.putExtra(EXTRA_ITEM_ID, itemId)
        context.startActivity(intentStartItemActivity)
    }

}