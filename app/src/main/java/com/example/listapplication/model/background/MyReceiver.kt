package com.example.listapplication.model.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.listapplication.view.ui.details.EXTRA_ITEM_ID
import com.example.listapplication.view.ui.details.ItemDetailsActivity
import com.example.listapplication.view.ui.list.PREFERENCES_ITEM
import com.example.listapplication.view.ui.list.PREFERENCES_NAME

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemId: Int = context
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getInt(PREFERENCES_ITEM, -1)
        Log.d("Receiver", "Item value $itemId")

        val intentStartItemActivity = Intent(context, ItemDetailsActivity::class.java)
        intentStartItemActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intentStartItemActivity.putExtra(EXTRA_ITEM_ID, itemId)
        context.startActivity(intentStartItemActivity)
    }

}