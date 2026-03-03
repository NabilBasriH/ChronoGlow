package com.nbh.chronoglow.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbh.chronoglow.ui.theme.ChronoGlowTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = hiltViewModel()) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    BoxWithConstraints(modifier = modifier) {
        if (maxWidth > 600.dp) {
            HomeScreenLandscape(
                uiState = uiState,
                onStart = { homeViewModel.startTimer() },
                onPause = { homeViewModel.pauseTimer() },
                onReset = { homeViewModel.resetTimer() },
                changeSession = { sessionMode -> homeViewModel.changeSession(sessionMode) }
            )
        } else {
            HomeScreenPortrait(
                uiState = uiState,
                onStart = { homeViewModel.startTimer() },
                onPause = { homeViewModel.pauseTimer() },
                onReset = { homeViewModel.resetTimer() },
                changeSession = { sessionMode -> homeViewModel.changeSession(sessionMode) }
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    ChronoGlowTheme {
        HomeScreen()
    }
}