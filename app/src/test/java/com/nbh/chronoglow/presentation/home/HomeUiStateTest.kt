package com.nbh.chronoglow.presentation.home

import com.nbh.chronoglow.domain.model.SessionMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeUiStateTest {

    @Test
    fun `progress is calculated correctly`() {
        val state = HomeUiState(
            sessionMode = SessionMode.FOCUS, // 1500s
            currentTime = 750L
        )
        assertEquals(0.5f, state.progress, 0.001f)
    }

    @Test
    fun `remainingTime is calculated correctly`() {
        val state = HomeUiState(
            sessionMode = SessionMode.FOCUS, // 1500s
            currentTime = 500L
        )
        assertEquals(1000L, state.remainingTime)
    }

    @Test
    fun `runningNotFinished is true when running and time left`() {
        val state = HomeUiState(
            sessionMode = SessionMode.FOCUS,
            isRunning = true,
            currentTime = 1499L
        )
        assertTrue(state.runningNotFinished)
    }

    @Test
    fun `runningNotFinished is false when not running`() {
        val state = HomeUiState(
            isRunning = false,
            currentTime = 100L
        )
        assertFalse(state.runningNotFinished)
    }

    @Test
    fun `runningNotFinished is false when time is up`() {
        val state = HomeUiState(
            sessionMode = SessionMode.FOCUS,
            isRunning = true,
            currentTime = 1500L
        )
        assertFalse(state.runningNotFinished)
    }
}