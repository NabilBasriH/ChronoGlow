package com.nbh.chronoglow.presentation.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nbh.chronoglow.R
import com.nbh.chronoglow.domain.model.SessionMode
import com.nbh.chronoglow.domain.model.SessionMode.FOCUS
import com.nbh.chronoglow.domain.model.SessionMode.LONG_BREAK
import com.nbh.chronoglow.domain.model.SessionMode.SHORT_BREAK

@Composable
fun SessionTitle(modifier: Modifier = Modifier, sessionMode: SessionMode) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = when (sessionMode) {
            FOCUS -> stringResource(R.string.focus_session)
            SHORT_BREAK -> stringResource(R.string.short_break).uppercase()
            LONG_BREAK -> stringResource(R.string.long_break).uppercase()
        },
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SessionTitlePreview() {
    SessionTitle(sessionMode = FOCUS)
}