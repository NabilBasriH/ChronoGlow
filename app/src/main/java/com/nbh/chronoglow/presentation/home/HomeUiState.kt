package com.nbh.chronoglow.presentation.home

import com.nbh.chronoglow.domain.model.SessionMode

data class HomeUiState(
    val sessionMode: SessionMode = SessionMode.FOCUS,
    val isRunning: Boolean = false,
    val currentTime: Long = 0L
) {
    val progress: Float
        get() = currentTime / totalTime.toFloat()

    val runningNotFinished: Boolean =
        isRunning && currentTime < totalTime

    val totalTime: Long
        get() = sessionMode.duration
}