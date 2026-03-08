package com.nbh.chronoglow.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbh.chronoglow.R
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.domain.model.SessionMode.FOCUS
import com.nbh.chronoglow.ui.theme.ChronoGlowTheme

@Composable
fun ControlButtons(
    modifier: Modifier = Modifier,
    sessionMode: SessionMode,
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val primaryColor =
            if (sessionMode == FOCUS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        val stateDescription =
            if (isRunning) stringResource(R.string.timer_running) else stringResource(R.string.timer_paused)
        val resetTimer = stringResource(R.string.reset_timer)

        Button(
            modifier = Modifier
                .width(250.dp)
                .padding(16.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50.dp),
                    clip = true,
                    ambientColor = primaryColor,
                    spotColor = primaryColor
                )
                .semantics { this.stateDescription = stateDescription },
            onClick = { if (isRunning) onPause() else onStart() },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = if (isRunning) stringResource(R.string.pause) else stringResource(R.string.start),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.surface
            )

        }
        Button(
            modifier = Modifier.semantics { contentDescription = resetTimer },
            onClick = { onReset() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.reset),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ControlButtonsPreview() {
    ChronoGlowTheme {
        ControlButtons(
            modifier = Modifier.background(Color.Black),
            isRunning = false,
            sessionMode = FOCUS,
            onStart = {},
            onPause = {},
            onReset = {}
        )
    }
}