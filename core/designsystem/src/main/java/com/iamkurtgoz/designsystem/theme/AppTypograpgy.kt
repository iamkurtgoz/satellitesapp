/*
 * Copyright 2024 Mehmet KURTGOZ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iamkurtgoz.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.iamkurtgoz.designsystem.R

val appFonts = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_thin, FontWeight.Thin),
)

data class AppTypography(
    val displayXl: TextStyle,
    val displayLg: TextStyle,
    val displayMd: TextStyle,
    val displaySm: TextStyle,
    val displayXs: TextStyle,
    val textXl: TextStyle,
    val textLg: TextStyle,
    val textMd: TextStyle,
    val textSm: TextStyle,
    val textXs: TextStyle,
)

internal val defaultTypography = AppTypography(
    displayXl = TextStyle(
        fontFamily = appFonts,
        fontSize = 60.sp,
        lineHeight = 72.sp,
        letterSpacing = TextUnit.Unspecified,
    ),
    displayLg = TextStyle(
        fontFamily = appFonts,
        fontSize = 48.sp,
        lineHeight = 60.sp,
        letterSpacing = TextUnit.Unspecified,
    ),
    displayMd = TextStyle(
        fontFamily = appFonts,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = TextUnit.Unspecified,
    ),
    displaySm = TextStyle(
        fontFamily = appFonts,
        fontSize = 30.sp,
        lineHeight = TextUnit.Unspecified,
    ),
    displayXs = TextStyle(
        fontFamily = appFonts,
        fontSize = 24.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = 0.sp,
    ),
    textXl = TextStyle(
        fontFamily = appFonts,
        fontSize = 20.sp,
        lineHeight = TextUnit.Unspecified,
    ),
    textLg = TextStyle(
        fontFamily = appFonts,
        fontSize = 18.sp,
        lineHeight = TextUnit.Unspecified,
    ),
    textMd = TextStyle(
        fontFamily = appFonts,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = TextUnit.Unspecified,
    ),
    textSm = TextStyle(
        fontFamily = appFonts,
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified,
    ),
    textXs = TextStyle(
        fontFamily = appFonts,
        fontSize = 12.sp,
        lineHeight = TextUnit.Unspecified,
    ),
)

internal val LocalAppTypographies =
    compositionLocalOf<AppTypography> { error("No typography provided!") }

val appTypography = Typography(
    displayLarge = defaultTypography.displayXl,
    displayMedium = defaultTypography.displayLg,
    displaySmall = defaultTypography.displayMd,
    headlineLarge = defaultTypography.displayMd,
    headlineMedium = defaultTypography.displaySm,
    headlineSmall = defaultTypography.displayXs,
    titleLarge = defaultTypography.displayXs,
    titleMedium = defaultTypography.textMd,
    titleSmall = defaultTypography.textSm,
    bodyLarge = defaultTypography.textMd,
    bodyMedium = defaultTypography.textSm,
    bodySmall = defaultTypography.textXs,
    labelLarge = defaultTypography.textSm,
    labelMedium = defaultTypography.textXs,
    labelSmall = defaultTypography.textXs,
)
