package com.nbh.chronoglow.presentation.home

import com.nbh.chronoglow.domain.model.SessionMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is correct`() {
        val state = viewModel.uiState.value
        assertEquals(SessionMode.FOCUS, state.sessionMode)
        assertFalse(state.isRunning)
        assertEquals(0L, state.currentTime)
    }

    @Test
    fun `startTimer updates isRunning and increments currentTime over time`() = runTest {
        viewModel.startTimer()
        assertTrue(viewModel.uiState.value.isRunning)

        advanceTimeBy(1001)
        assertEquals(1L, viewModel.uiState.value.currentTime)

        advanceTimeBy(2000)
        assertEquals(3L, viewModel.uiState.value.currentTime)
    }

    @Test
    fun `pauseTimer stops the timer and updates isRunning`() = runTest {
        viewModel.startTimer()
        advanceTimeBy(1001)
        
        viewModel.pauseTimer()
        
        assertFalse(viewModel.uiState.value.isRunning)
        val timeAfterPause = viewModel.uiState.value.currentTime
        
        advanceTimeBy(2000)
        assertEquals(timeAfterPause, viewModel.uiState.value.currentTime)
    }

    @Test
    fun `resetTimer stops timer and resets currentTime`() = runTest {
        viewModel.startTimer()
        advanceTimeBy(5001)
        
        viewModel.resetTimer()
        
        assertFalse(viewModel.uiState.value.isRunning)
        assertEquals(0L, viewModel.uiState.value.currentTime)
    }

    @Test
    fun `changeSession stops timer and resets state with new mode`() = runTest {
        viewModel.startTimer()
        advanceTimeBy(2001)
        
        viewModel.changeSession(SessionMode.SHORT_BREAK)
        
        val state = viewModel.uiState.value
        assertEquals(SessionMode.SHORT_BREAK, state.sessionMode)
        assertFalse(state.isRunning)
        assertEquals(0L, state.currentTime)
    }

    @Test
    fun `timer stops automatically when reaching duration`() = runTest {
        viewModel.changeSession(SessionMode.SHORT_BREAK) // 300s
        viewModel.startTimer()

        advanceTimeBy(300 * 1000L + 100)
        
        assertFalse(viewModel.uiState.value.isRunning)
        assertEquals(300L, viewModel.uiState.value.currentTime)
    }
}