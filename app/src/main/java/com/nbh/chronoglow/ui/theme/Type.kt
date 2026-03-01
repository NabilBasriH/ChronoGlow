package com.nbh.chronoglow.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nbh.chronoglow.R

val Inter = FontFamily(
    Font(R.font.inter_variable)
)

val Typography = Typography(
    // Timer Text
    displayLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 65.sp,
        lineHeight = 80.sp,
        letterSpacing = (-1).sp,
        fontFeatureSettings = "tnum"
    ),

    // Title
    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.5.sp
    ),

    // Buttons
    labelLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Buttons Text
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)