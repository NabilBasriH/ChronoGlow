package com.nbh.chronoglow.presentation.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeFormatterTest {

    @Test
    fun `formatTime with 0 seconds returns 00-00`() {
        assertEquals("00:00", formatTime(0))
    }

    @Test
    fun `formatTime with less than 60 seconds returns correct seconds`() {
        assertEquals("00:45", formatTime(45))
    }

    @Test
    fun `formatTime with exactly 60 seconds returns 01-00`() {
        assertEquals("01:00", formatTime(60))
    }

    @Test
    fun `formatTime with multiple minutes returns correct format`() {
        assertEquals("25:00", formatTime(1500))
        assertEquals("59:59", formatTime(3599))
    }

    @Test
    fun `formatTime with more than 60 minutes returns correct format`() {
        assertEquals("61:05", formatTime(3665))
    }
}