package com.nbh.chronoglow.presentation.core.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.app.NotificationCompat
import com.nbh.chronoglow.MainActivity
import com.nbh.chronoglow.R
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.presentation.home.HomeUiState
import com.nbh.chronoglow.presentation.utils.CrashlyticsHelper
import com.nbh.chronoglow.presentation.utils.formatTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerService : Service() {

    companion object {
        const val CHANNEL_ID = "timer_channel"
        const val NOTIFICATION_ID = 1

        const val ACTION_START = "ACTION_START"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_RESET = "ACTION_RESET"
        const val ACTION_CHANGE_SESSION = "ACTION_CHANGE_SESSION"
        const val EXTRA_SESSION_MODE = "EXTRA_SESSION_MODE"

        val timerState = MutableStateFlow(HomeUiState())
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var timerJob: Job? = null

    override fun onBind(intent: Intent?) = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTimer()
            ACTION_PAUSE -> pauseTimer()
            ACTION_RESET -> resetTimer()
            ACTION_CHANGE_SESSION -> {
                val mode = intent.getStringExtra(EXTRA_SESSION_MODE)
                    ?.let { SessionMode.valueOf(it) } ?: return START_STICKY
                changeSession(mode)
            }
        }
        return START_STICKY
    }

    private fun startTimer() {
        if (timerJob != null) return

        timerState.update { it.copy(isRunning = true) }
        startForeground(NOTIFICATION_ID, buildNotification())

        timerJob = serviceScope.launch {
            while (timerState.value.runningNotFinished) {
                delay(1000)
                timerState.update { it.copy(currentTime = it.currentTime + 1) }
                updateNotification()
            }
            triggerEndAlert()

            timerState.update { it.copy(isRunning = false, currentTime = 0L) }
            timerJob = null
            updateNotification()
        }
    }

    private fun pauseTimer() {
        timerJob?.cancel()
        timerJob = null
        timerState.update { it.copy(isRunning = false) }
        updateNotification()
    }

    private fun resetTimer() {
        timerJob?.cancel()
        timerJob = null
        timerState.update { it.copy(isRunning = false, currentTime = 0L) }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun changeSession(mode: SessionMode) {
        timerJob?.cancel()
        timerJob = null
        timerState.update { it.copy(isRunning = false, currentTime = 0L, sessionMode = mode) }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun buildNotification(): Notification {
        val state = timerState.value

        val openAppIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        val toggleAction = if (state.isRunning) {
            NotificationCompat.Action(
                0, getString(R.string.pause),
                pendingServiceIntent(ACTION_PAUSE)
            )
        } else {
            NotificationCompat.Action(
                0, getString(R.string.start),
                pendingServiceIntent(ACTION_START)
            )
        }

        val resetAction = NotificationCompat.Action(
            0, getString(R.string.reset),
            pendingServiceIntent(ACTION_RESET)
        )

        val sessionName = when (state.sessionMode) {
            SessionMode.FOCUS -> getString(R.string.focus)
            SessionMode.SHORT_BREAK -> getString(R.string.short_break)
            SessionMode.LONG_BREAK -> getString(R.string.long_break)
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.splash_logo)
            .setContentTitle(sessionName)
            .setContentText(formatTime(state.remainingTime))
            .setContentIntent(openAppIntent)
            .addAction(toggleAction)
            .addAction(resetAction)
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification() {
        getSystemService(NotificationManager::class.java)
            .notify(NOTIFICATION_ID, buildNotification())
    }

    private fun pendingServiceIntent(action: String): PendingIntent {
        return PendingIntent.getService(
            this, action.hashCode(),
            Intent(this, TimerService::class.java).apply { this.action = action },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun triggerEndAlert() {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val isStreamMuted = audioManager.isStreamMute(AudioManager.STREAM_MUSIC)

        val volumePercent = if (maxVolume > 0) currentVolume.toFloat() / maxVolume else 0f

        when {
            isStreamMuted || currentVolume == 0 -> vibrate()
            volumePercent < 0.3f -> {
                playSound(); vibrate()
            }

            else -> playSound()
        }
    }

    private fun playSound() {
        try {
            val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.bell_ding_sound)
            mediaPlayer.setOnCompletionListener { it.release() }
            mediaPlayer.start()
        } catch (e: Exception) {
            CrashlyticsHelper.logSoundError(e)
            vibrate()
        }
    }

    @Suppress("DEPRECATION")
    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator.vibrate(
                VibrationEffect.createWaveform(longArrayOf(0, 400, 200, 400), -1)
            )
        } else {
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 400, 200, 400), -1))
            } else {
                vibrator.vibrate(longArrayOf(0, 400, 200, 400), -1)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}