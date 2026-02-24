package com.nbh.chronoglow.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbh.chronoglow.presentation.home.HomeViewModel

@Composable
fun ProgressButtons(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .width(250.dp)
                .padding(16.dp),
            onClick = { if (uiState.isRunning) homeViewModel.startTimer() else homeViewModel.pauseTimer() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B81F4))
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = if (uiState.isRunning) "Start" else "Pause",
                fontSize = 16.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal
            )

        }
        Button(
            onClick = { homeViewModel.resetTimer() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Reset",
                fontSize = 16.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProgressButtonsPreview() {
    ProgressButtons(homeViewModel = HomeViewModel())
}