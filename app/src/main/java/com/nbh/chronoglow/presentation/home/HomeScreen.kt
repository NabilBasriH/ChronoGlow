package com.nbh.chronoglow.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.presentation.core.ControlButtons
import com.nbh.chronoglow.presentation.core.GlowRing
import com.nbh.chronoglow.presentation.core.SessionSelector
import com.nbh.chronoglow.presentation.core.SessionTitle
import com.nbh.chronoglow.presentation.core.TimerText
import com.nbh.chronoglow.ui.theme.ChronoGlowTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SessionTitle(sessionMode = SessionMode.FOCUS)
        Spacer(Modifier.weight(1f))
        Box(contentAlignment = Alignment.Center) {
            GlowRing(
                modifier = Modifier.size(300.dp),
                progress = 0.5f,
                ringColor = MaterialTheme.colorScheme.primary
            )
            TimerText(remainingTime = 1500L)
        }
        Spacer(Modifier.height(32.dp))
        ControlButtons(
            isRunning = true,
            onStart = {},
            onPause = {},
            onReset = {}
        )
        Spacer(Modifier.weight(1f))
        SessionSelector(sessionMode = SessionMode.FOCUS)
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ChronoGlowTheme {
        HomeScreen()
    }
}