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

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    primaryColor: Color,
    backgroundPrimary: Color,
    foregroundPrimary: Color,
    foregroundSecondary: Color,
    searchBarBackground: Color,
) {
    var primaryColor by mutableStateOf(primaryColor)
        private set
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set
    var foregroundPrimary by mutableStateOf(foregroundPrimary)
        private set
    var foregroundSecondary by mutableStateOf(foregroundSecondary)
        private set
    var searchBarBackground by mutableStateOf(searchBarBackground)
        private set

    fun update(other: AppColors) {
        primaryColor = other.primaryColor
        backgroundPrimary = other.backgroundPrimary
        foregroundPrimary = other.foregroundPrimary
        foregroundSecondary = other.foregroundSecondary
        searchBarBackground = other.searchBarBackground
    }
}

@Composable
fun AppColors.asMaterialColors() = MaterialTheme.colorScheme.copy(
    primary = this.primaryColor,
    onPrimary = this.foregroundPrimary,
    primaryContainer = this.foregroundPrimary,
    background = this.backgroundPrimary,
    surface = this.backgroundPrimary,
)

internal val LocalAppColors = compositionLocalOf<AppColors> { error("No Color provided!") }
