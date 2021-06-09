package com.nrw.touchinjector

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class TouchInjectorJob: JobIntentService() {
    val JOB_ID: Int = 201

    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, TouchInjectorJob::class.java, JOB_ID, work)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TOUCH_INJECTOR", "Job execution started")
        var intent: Intent = Intent(this, TouchInjectorJob::class.java)
        enqueueWork(this, intent)
    }

    override fun onHandleWork(intent: Intent) {
        Thread.sleep(1000)
        Log.d("TOUCH_INJECTOR", "here");
    }
}