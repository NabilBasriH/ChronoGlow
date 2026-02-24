package com.nbh.chronoglow.presentation.core

import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.presentation.home.HomeViewModel

@Composable
fun SessionSelector(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val selectedMode = uiState.sessionMode

    ElevatedCard(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedMode == SessionMode.FOCUS) Color.Transparent else Color.Gray
                )
            ) {
                Text(
                    text = "Focus",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedMode == SessionMode.FOCUS) Color.Transparent else Color.Gray
                )
            ) {
                Text(
                    text = "Short Break",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedMode == SessionMode.FOCUS) Color.Transparent else Color.Gray
                )
            ) {
                Text(
                    text = "Long Break",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SessionSelectorPreview() {
    SessionSelector(homeViewModel = HomeViewModel())
}