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
package com.iamkurtgoz.commonui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.iamkurtgoz.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
object AppToolbar {

    @Composable
    fun Toolbar(
        modifier: Modifier = Modifier,
        topAppBarState: TopAppBarState = rememberTopAppBarState(),
        leftContent: (@Composable AppToolbar.() -> Unit)? = null,
        centerContent: (@Composable AppToolbar.() -> Unit)? = null,
        rightContent: (@Composable RowScope.() -> Unit)? = null,
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

        Surface(
            modifier = modifier,
            shadowElevation = AppTheme.dimens.Dp3,
            color = AppTheme.colors.foregroundPrimary,
        ) {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.backgroundPrimary,
                    titleContentColor = AppTheme.colors.foregroundPrimary,
                ),
                title = {
                    centerContent?.invoke(this)
                },
                navigationIcon = {
                    leftContent?.invoke(this)
                },
                actions = {
                    rightContent?.invoke(this)
                },
                scrollBehavior = scrollBehavior,
            )
        }
    }
}

object AppToolbarFields {

    @Composable
    fun NavigateIcon(
        modifier: Modifier = Modifier,
        size: Dp = AppTheme.dimens.Dp24,
        iconTint: Color = AppTheme.colors.foregroundPrimary,
        onClick: () -> Unit,
    ) {
        IconButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Navigate Button Arrow Back",
                modifier = Modifier
                    .size(size),
                tint = iconTint,
            )
        }
    }

    @Composable
    fun Title(text: String, modifier: Modifier = Modifier) = TitleWithSubTitle(text = text, subText = "", modifier = modifier)

    @Composable
    fun TitleWithSubTitle(text: String, subText: String, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = AppTheme.typography.textXl.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            AnimatedVisibility(visible = subText.isNotEmpty()) {
                Text(
                    subText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.textXs.copy(
                        fontWeight = FontWeight.Light,
                    ),
                )
            }
        }
    }
}
