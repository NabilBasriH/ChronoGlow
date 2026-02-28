package com.nbh.chronoglow.presentation.core

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nbh.chronoglow.presentation.utils.formatTime

@Composable
fun TimerText(modifier: Modifier = Modifier, remainingTime: Long) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = formatTime(remainingTime),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun TimerTextPreview() {
    TimerText(remainingTime = 10000)
}