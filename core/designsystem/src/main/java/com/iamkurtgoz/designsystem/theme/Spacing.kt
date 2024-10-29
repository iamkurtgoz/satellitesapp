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

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
data class AppSpacing(
    val spacingNone: Dp,
    val spacingTiniest: Dp,
    val spacingTiny: Dp,
    val spacingSmallest: Dp,
    val spacingSmall: Dp,
    val spacingMedium: Dp,
    val spacingLarge: Dp,
    val spacingHuge: Dp,
)

val appSpacing = AppSpacing(
    spacingNone = appDimens.Dp0,
    spacingTiniest = appDimens.Dp1,
    spacingTiny = appDimens.Dp2,
    spacingSmallest = appDimens.Dp4,
    spacingSmall = appDimens.Dp8,
    spacingMedium = appDimens.Dp16,
    spacingHuge = appDimens.Dp24,
    spacingLarge = appDimens.Dp32,
)

internal val LocalAppSpacing = staticCompositionLocalOf<AppSpacing> { error("No spacing provided") }
