package com.example.listapplication.view

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.listapplication.R

const val ONGOING_NOTIFICATION_ID = 100
const val CHANNEL_ID = "foreground_channel"
const val ITEM_ACTION = "com.example.listapplication.ITEM"

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val receiverIntent = Intent(ITEM_ACTION)
        receiverIntent.setPackage(packageName)
        val pendingIntent : PendingIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0)

        createNotificationChannel()
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(this.getString(R.string.app_name))
            .setContentText("Text")
            .setSmallIcon(android.R.drawable.btn_default)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(ONGOING_NOTIFICATION_ID, notification)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = "Foreground service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}