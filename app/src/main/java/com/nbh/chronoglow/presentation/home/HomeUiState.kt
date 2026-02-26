package com.nbh.chronoglow.presentation.home

import com.nbh.chronoglow.domain.model.SessionMode

data class HomeUiState(
    val sessionMode: SessionMode = SessionMode.FOCUS,
    val isRunning: Boolean = false,
    val currentTime: Long = 0L
) {
    val totalTime: Long
        get() = sessionMode.duration

    val progress: Float
        get() = currentTime / totalTime.toFloat()

    val remainingTime: Long
        get() = totalTime - currentTime

    val runningNotFinished: Boolean =
        isRunning && currentTime < totalTime
}