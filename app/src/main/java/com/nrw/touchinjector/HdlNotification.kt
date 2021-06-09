package com.nrw.touchinjector

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class HdlNotification {
    val channel: NotificationChannel
    val context: Context;
    val channelName: String = "notiChannel"
    val channelDesc: String = "notiChannelDesc"
    val channelID: String = "notiChannelID"
    lateinit var notiManager: NotificationManager

    constructor(ctx: Context/*, notificationManager: NotificationManager?*/) {
        context = ctx;
        /* Since our min SDK is 26, it doesn't need to check SDK version(>= 26) */
        channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
    }

    fun buildNotification(): Notification?{
        // Register the channel with the system
        notiManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notiManager?.createNotificationChannel(channel)

        val intent = Intent(context, TouchInjectorJob::class.java)
        // PendingIntent has getService, getActivity, getBroadcast
        val pi: PendingIntent = PendingIntent.getService(context, 0, intent, 0)
        val b: NotificationCompat.Builder = NotificationCompat.Builder(context, channelID)
        b.setOngoing(true)
            .setContentTitle("TouchInjector")
            .setContentText("StartTouchInject")
            .setSmallIcon(android.R.drawable.btn_plus)
            .setContentIntent(pi)
            .setTicker("What?")
        return (b.build())
    }
}