package com.nbh.chronoglow.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbh.chronoglow.domain.model.SessionMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<HomeUiState>
        field = MutableStateFlow(HomeUiState())

    private var timerJob: Job? = null

    fun startTimer() {
        uiState.update {
            it.copy(isRunning = true)
        }

        timerJob = viewModelScope.launch {
            while (uiState.value.runningNotFinished) {
                delay(1000)
                uiState.update { state ->
                    state.copy(currentTime = state.currentTime + 1)
                }
            }
            uiState.update { state ->
                state.copy(isRunning = false)
            }
        }
    }

    fun pauseTimer() {
        uiState.update {
            it.copy(isRunning = false)
        }
        timerJob?.cancel()
    }

    fun resetTimer() {
        uiState.update {
            it.copy(isRunning = false, currentTime = 0L)
        }
        timerJob?.cancel()
    }

    fun changeSession(mode: SessionMode) {
        uiState.update {
            it.copy(
                sessionMode = mode,
                currentTime = 0L,
                isRunning = false
            )
        }
    }
}