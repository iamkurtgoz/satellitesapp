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
package com.iamkurtgoz.satellites.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.designsystem.theme.AppThemeSurface

@Composable
internal fun AppScreen(viewModel: AppScreenViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigationController = rememberNavController()

    if (!state.keepSplashScreenOn) {
        Scaffold(
            navigationController = navigationController,
            state = state,
        )
    }
}

@Composable
private fun Scaffold(
    navigationController: NavHostController,
    state: AppScreenContract.State,
) {
    AppThemeScaffold {
        AppScreenContent(
            navigationController = navigationController,
            state = state,
        )
    }
}

@AppWithNightModePreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeSurface {
            Scaffold(
                navigationController = rememberNavController(),
                state = AppScreenContract.State(isLoading = false),
            )
        }
    }
}
