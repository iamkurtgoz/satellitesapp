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
package com.iamkurtgoz.feature.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.iamkurtgoz.feature.detail.DetailScreen
import com.iamkurtgoz.navigation.DetailScreenRoute

fun NavController.navigateToDetailScreen(id: Int, name: String, navOptions: NavOptions? = null) {
    val route = DetailScreenRoute(id = id, name = name)
    this.navigate(route, navOptions)
}

fun NavGraphBuilder.detailScreen(
    popBackStack: () -> Unit,
) {
    composable<DetailScreenRoute> {
        DetailScreen(
            popBackStack = popBackStack,
        )
    }
}
