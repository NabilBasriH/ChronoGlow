package com.nbh.chronoglow.presentation.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nbh.chronoglow.ui.theme.ChronoGlowTheme

@Composable
fun GlowRing(
    modifier: Modifier = Modifier,
    progress: Float,
    ringColor: Color = Color.Cyan,
    ringThickness: Dp = 10.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        val stroke = ringThickness.toPx()

        for (i in 4 downTo 1) {
            drawArc(
                color = ringColor.copy(alpha = 0.01f * i),
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress,
                useCenter = false,
                style = Stroke(
                    width = stroke + i * 12f,
                    cap = StrokeCap.Round
                )
            )
        }

        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = radius,
            style = Stroke(width = ringThickness.toPx())
        )

        drawArc(
            color = ringColor,
            startAngle = -90f,
            sweepAngle = 360 * animatedProgress,
            useCenter = false,
            style = Stroke(width = stroke / 1.1f, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun TimerRing(modifier: Modifier = Modifier) {
    var isRunning by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
}

@Preview(showSystemUi = true)
@Composable
private fun GlowRingPreview() {
    ChronoGlowTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlowRing(progress = 0.5f, modifier = Modifier.size(200.dp))
        }
    }
}