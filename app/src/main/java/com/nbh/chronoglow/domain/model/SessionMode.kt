package com.nbh.chronoglow.domain.model

enum class SessionMode(
    val duration: Long
) {
    FOCUS(1500L),
    SHORT_BREAK(300L),
    LONG_BREAK(900L)
}