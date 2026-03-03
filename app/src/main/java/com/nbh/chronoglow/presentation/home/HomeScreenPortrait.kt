package com.nbh.chronoglow.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.domain.model.SessionMode.FOCUS
import com.nbh.chronoglow.presentation.core.ControlButtons
import com.nbh.chronoglow.presentation.core.GlowRing
import com.nbh.chronoglow.presentation.core.SessionSelector
import com.nbh.chronoglow.presentation.core.SessionTitle
import com.nbh.chronoglow.presentation.core.TimerText
import com.nbh.chronoglow.ui.theme.ChronoGlowTheme

@Composable
fun HomeScreenPortrait(
    uiState: HomeUiState,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    changeSession: (SessionMode) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SessionTitle(sessionMode = uiState.sessionMode)
        Spacer(Modifier.weight(1f))
        Box(contentAlignment = Alignment.Center) {
            GlowRing(
                modifier = Modifier.size(270.dp),
                progress = uiState.progress,
                ringColor = if (uiState.sessionMode == FOCUS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
            TimerText(remainingTime = uiState.remainingTime)
        }
        Spacer(Modifier.weight(1f))
        ControlButtons(
            sessionMode = uiState.sessionMode,
            isRunning = uiState.isRunning,
            onStart = { onStart() },
            onPause = { onPause() },
            onReset = { onReset() },
        )
        Spacer(Modifier.weight(1f))
        SessionSelector(
            sessionMode = uiState.sessionMode,
            changeSession = { sessionMode -> changeSession(sessionMode) })
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPortraitPreview() {
    ChronoGlowTheme {
        HomeScreenPortrait(
            uiState = HomeUiState(),
            onStart = {},
            onPause = {},
            onReset = {},
            changeSession = {},
        )
    }
}