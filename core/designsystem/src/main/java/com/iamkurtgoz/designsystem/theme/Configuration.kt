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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

object AppConfiguration {
    @Composable
    fun getScreenWidth() = LocalConfiguration.current.screenWidthDp

    @Composable
    fun getHalfScreenWidth() = LocalConfiguration.current.screenWidthDp / 2

    @Composable
    fun getScreenWidthDp() = getScreenWidth().dp

    @Composable
    fun getHalfScreenWidthDp() = getHalfScreenWidth().dp

    @Composable
    fun getScreenHeight() = LocalConfiguration.current.screenHeightDp

    @Composable
    fun getHalfScreenHeight() = LocalConfiguration.current.screenHeightDp / 2

    @Composable
    fun getScreenHeightDp() = getScreenHeight().dp

    @Composable
    fun getHalfScreenHeightDp() = getHalfScreenHeight().dp
}

internal val LocalAppConfiguration =
    compositionLocalOf<AppConfiguration> { error("No Shapes provided") }

val appConfiguration = AppConfiguration
