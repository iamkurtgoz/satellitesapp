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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.list.navigation.listScreen
import com.iamkurtgoz.navigation.AppRoute
import com.iamkurtgoz.navigation.ListScreenRoute

@Composable
internal fun AppScreenContent(
    navigationController: NavHostController,
    state: AppScreenContract.State,
) {
    if (!state.keepSplashScreenOn) {
        NavHost(
            navController = navigationController,
            route = AppRoute::class,
            startDestination = ListScreenRoute,
            builder = {
                // List Screen
                listScreen()
            },
        )
    }
}

private fun NavController.navigatePopUpToInclusive(): NavOptions.Builder {
    val option = NavOptions.Builder()
    option.setPopUpTo(currentDestination?.route, true)
    return option
}

fun NavController.navigateAndClearBackStack(): NavOptions.Builder {
    val option = NavOptions.Builder()
    option.setPopUpTo(destinationId = 0, inclusive = true, saveState = false)
    return option
}

@Preview(showBackground = true)
@Composable
private fun AppScreenPreview() {
    AppTheme {
        AppThemeScaffold {
            AppScreenContent(
                navigationController = rememberNavController(),
                state = AppScreenContract.State(isLoading = false),
            )
        }
    }
}