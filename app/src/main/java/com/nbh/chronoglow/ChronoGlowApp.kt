package com.nbh.chronoglow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.nbh.chronoglow.presentation.core.service.TimerService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChronoGlowApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                TimerService.CHANNEL_ID,
                "ChronoGlow",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Show the state of the timer"
            }
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }
}