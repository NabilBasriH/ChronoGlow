package com.nbh.chronoglow.presentation.utils

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.nbh.chronoglow.domain.model.SessionMode

object CrashlyticsHelper {
    fun logTimerAction(
        action: TimerAction,
        sessionMode: SessionMode,
        isRunning: Boolean,
        remainingTime: Long
    ) {
        Firebase.crashlytics.apply {
            setCustomKeys {
                key("last_action", action.name)
                key("session_mode", sessionMode.name)
                key("is_running", isRunning)
                key("remaining_time", remainingTime)
            }
            log("${action.name} | session=$sessionMode | running=$isRunning | remaining=$remainingTime")
        }
    }
}

enum class TimerAction {
    START, PAUSE, RESET, SESSION_CHANGE
}