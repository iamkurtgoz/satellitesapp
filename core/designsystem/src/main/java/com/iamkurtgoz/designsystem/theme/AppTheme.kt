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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.iamkurtgoz.designsystem.theme.color.AppColorPalette
import com.iamkurtgoz.designsystem.theme.color.darkColors
import com.iamkurtgoz.designsystem.theme.color.lightColors

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) AppColorPalette.darkColors else AppColorPalette.lightColors
    ProvideAppResources(
        typography = defaultTypography,
        colors = colorScheme,
        shapes = appShapes,
        spacing = appSpacing,
        appDimens = appDimens,
        appConfiguration = appConfiguration,
    ) {
        MaterialTheme(
            colorScheme = colorScheme.asMaterialColors(),
            typography = appTypography,
            shapes = mediumShapes,
            content = content,
        )
    }
}

@Composable
fun AppThemeSurface(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier.fillMaxSize(),
        contentColor = AppTheme.colors.foregroundPrimary,
        content = content,
    )
}

@Composable
fun AppThemeScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = AppTheme.colors.backgroundPrimary,
    contentColor: Color = AppTheme.colors.foregroundPrimary,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackBarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content,
    )
}

@Composable
fun ProvideAppResources(
    typography: AppTypography,
    colors: AppColors,
    shapes: AppShapes,
    spacing: AppSpacing,
    appDimens: AppDimens,
    appConfiguration: AppConfiguration,
    content: @Composable () -> Unit,
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(
        LocalAppTypographies provides typography,
        LocalAppColors provides colorPalette,
        LocalAppShapes provides shapes,
        LocalAppSpacing provides spacing,
        LocalAppContentColor provides colorPalette.foregroundPrimary,
        LocalAppDimens provides appDimens,
        LocalAppConfiguration provides appConfiguration,
        LocalDensity provides Density(LocalDensity.current.density, 1f),
    ) {
        ProvideTextStyle(value = typography.displayMd, content = content)
    }
}

object AppTheme {
    val typography: AppTypography
        @Composable
        get() = LocalAppTypographies.current

    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current

    val spacing: AppSpacing
        @Composable
        get() = LocalAppSpacing.current

    val dimens: AppDimens
        @Composable
        get() = LocalAppDimens.current

    val configuration: AppConfiguration
        @Composable
        get() = LocalAppConfiguration.current
}
