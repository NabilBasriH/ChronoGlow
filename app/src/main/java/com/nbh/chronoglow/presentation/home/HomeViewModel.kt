package com.nbh.chronoglow.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModel
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.presentation.core.service.TimerService
import com.nbh.chronoglow.presentation.utils.CrashlyticsHelper
import com.nbh.chronoglow.presentation.utils.TimerAction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@param:ApplicationContext private val context: Context) :
    ViewModel() {
    val uiState: StateFlow<HomeUiState> = TimerService.timerState.asStateFlow()

    fun startTimer() {
        CrashlyticsHelper.logTimerAction(
            action = TimerAction.START,
            sessionMode = uiState.value.sessionMode,
            isRunning = uiState.value.isRunning,
            remainingTime = uiState.value.remainingTime
        )
        sendAction(TimerService.ACTION_START)
    }

    fun pauseTimer() {
        CrashlyticsHelper.logTimerAction(
            action = TimerAction.PAUSE,
            sessionMode = uiState.value.sessionMode,
            isRunning = uiState.value.isRunning,
            remainingTime = uiState.value.remainingTime
        )
        sendAction(TimerService.ACTION_PAUSE)
    }

    fun resetTimer() {
        CrashlyticsHelper.logTimerAction(
            action = TimerAction.RESET,
            sessionMode = uiState.value.sessionMode,
            isRunning = uiState.value.isRunning,
            remainingTime = uiState.value.remainingTime
        )
        sendAction(TimerService.ACTION_RESET)
    }

    fun changeSession(mode: SessionMode) {
        CrashlyticsHelper.logTimerAction(
            action = TimerAction.SESSION_CHANGE,
            sessionMode = uiState.value.sessionMode,
            isRunning = uiState.value.isRunning,
            remainingTime = uiState.value.remainingTime
        )
        sendAction(TimerService.ACTION_CHANGE_SESSION, mode)
    }

    private fun sendAction(action: String, sessionMode: SessionMode? = null) {
        Intent(context, TimerService::class.java).also { intent ->
            intent.action = action
            sessionMode?.let { intent.putExtra(TimerService.EXTRA_SESSION_MODE, it.name) }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (action == TimerService.ACTION_START) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            } else {
                context.startService(intent)
            }
        }
    }
}