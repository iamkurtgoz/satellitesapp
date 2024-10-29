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
package com.iamkurtgoz.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.resources.R as resourceR

@Composable
internal fun ListItemRow(
    index: Int,
    isActive: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (index != ListItemRowStatic.FIRST_INDEX) {
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = AppTheme.spacing.spacingSmall),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = isActive, onClick = onClickAction),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .width(AppTheme.dimens.Dp128),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isActive) Color.Green.copy(alpha = ListItemRowStatic.CIRCLE_COLOR_ALPHA) else Color.Red.copy(alpha = ListItemRowStatic.CIRCLE_COLOR_ALPHA))
                        .size(AppTheme.dimens.Dp16),
                )

                Column(
                    modifier = Modifier
                        .padding(start = AppTheme.spacing.spacingHuge),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        style = AppTheme.typography.textSm.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = if (isActive) AppTheme.colors.foregroundPrimary else AppTheme.colors.foregroundSecondary.copy(alpha = ListItemRowStatic.PASSIVE_TEXT_COLOR_ALPHA),
                    )
                    Text(
                        text = stringResource(if (isActive) resourceR.string.active_status else resourceR.string.passive_status),
                        style = AppTheme.typography.textXs,
                        color = if (isActive) AppTheme.colors.foregroundSecondary else AppTheme.colors.foregroundSecondary.copy(alpha = ListItemRowStatic.PASSIVE_TEXT_COLOR_ALPHA),
                    )
                }
            }
        }
    }
}

private object ListItemRowStatic {
    const val FIRST_INDEX: Int = 0
    const val SECOND_INDEX: Int = 1
    const val THIRD_INDEX: Int = 2
    const val CIRCLE_COLOR_ALPHA: Float = 0.8f
    const val PASSIVE_TEXT_COLOR_ALPHA: Float = 0.5f
}

@AppWithNightModePreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeScaffold {
            LazyColumn {
                item {
                    ListItemRow(
                        index = ListItemRowStatic.FIRST_INDEX,
                        isActive = false,
                        title = "AAA",
                        onClickAction = { },
                    )
                }

                item {
                    ListItemRow(
                        index = ListItemRowStatic.SECOND_INDEX,
                        isActive = true,
                        title = "BBBBBBB",
                        onClickAction = { },
                    )
                }

                item {
                    ListItemRow(
                        index = ListItemRowStatic.THIRD_INDEX,
                        isActive = true,
                        title = "CCC",
                        onClickAction = { },
                    )
                }
            }
        }
    }
}
