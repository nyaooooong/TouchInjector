package com.nrw.touchinjector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.nrw.touchinjector.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("Key Code:", keyCode.toString())
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Intent(this, TouchInjectorService::class.java).also { intent -> startService(intent)}
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
                Intent(this, TouchInjectorService::class.java).also { intent -> stopService(intent)}
            }
        }
        return false // If you handled the event, return true. If you want to allow the event to be handled by the next receiver, return false.
    }
}