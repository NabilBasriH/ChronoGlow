package com.nbh.chronoglow.presentation.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbh.chronoglow.R
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.domain.model.SessionMode.FOCUS
import com.nbh.chronoglow.domain.model.SessionMode.LONG_BREAK
import com.nbh.chronoglow.domain.model.SessionMode.SHORT_BREAK
import com.nbh.chronoglow.ui.theme.ChronoGlowTheme

@Composable
fun SessionSelector(modifier: Modifier = Modifier, sessionMode: SessionMode) {
    ElevatedCard(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
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
                        if (sessionMode == FOCUS) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(R.string.focus),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (sessionMode == SHORT_BREAK) Color.Gray else Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(R.string.short_break),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (sessionMode == LONG_BREAK) Color.Gray else Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(R.string.long_break),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SessionSelectorPreview() {
    ChronoGlowTheme {
        SessionSelector(sessionMode = FOCUS)
    }
}