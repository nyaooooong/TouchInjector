package com.nrw.touchinjector

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TouchInjectorService : Service() {

    private val debugUI: DebugUI = DebugUI(this)
    private val notificationHandle: HdlNotification = HdlNotification(this)
    private val FOREGROUND_ID: Int = 1338

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()

        debugUI.showPopup();
        startForeground(FOREGROUND_ID, notificationHandle.buildNotification())
    }

    override fun onDestroy() {
        super.onDestroy()
        debugUI.closePopup()
    }
}