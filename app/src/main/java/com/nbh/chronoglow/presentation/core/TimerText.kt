package com.nbh.chronoglow.presentation.core

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.nbh.chronoglow.R
import com.nbh.chronoglow.presentation.utils.formatTime
import com.nbh.chronoglow.presentation.utils.getTimeParts

@Composable
fun TimerText(modifier: Modifier = Modifier, remainingTime: Long) {
    val (minutes, seconds) = getTimeParts(remainingTime)
    val accessibleText = stringResource(R.string.time_remaining, minutes, seconds)
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .semantics { contentDescription = accessibleText },
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