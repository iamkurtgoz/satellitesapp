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

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.iamkurtgoz.designsystem.internal.AppPreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.resources.R as resourceR

/**
 * Component Figma Address
 * url: https://www.figma.com/community/file/1009457657547733168/search-bar-search
 */
@Composable
fun SearchComponent(
    value: String,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = AppTheme.shapes.radiusXl,
    @StringRes hint: Int = resourceR.string.search_hint,
    onValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(AppTheme.dimens.Dp36)
            .clip(shape)
            .background(AppTheme.colors.searchBarBackground),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(resourceR.drawable.img_magnifying_glass),
            modifier = Modifier
                .padding(horizontal = AppTheme.spacing.spacingSmall)
                .size(AppTheme.dimens.Dp18),
            contentDescription = "Search logo",
        )

        Box(
            modifier = Modifier
                .weight(SearchComponentStatic.FULL_WEIGHT),
        ) {
            if (value.isEmpty()) {
                Text(
                    text = stringResource(hint),
                    style = AppTheme.typography.textMd,
                    color = AppTheme.colors.foregroundSecondary,
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = AppTheme.typography.textMd,
                singleLine = true,
            )
        }

        AnimatedVisibility(value.isNotEmpty()) {
            IconButton(
                onClick = {
                    onValueChange.invoke("")
                },
                content = {
                    Image(
                        painter = painterResource(resourceR.drawable.img_close),
                        modifier = Modifier
                            .padding(horizontal = AppTheme.spacing.spacingSmall)
                            .size(AppTheme.dimens.Dp18),
                        contentDescription = "Close logo",
                    )
                },
            )
        }
    }
}

private object SearchComponentStatic {
    const val FULL_WEIGHT: Float = 1f
}

@AppPreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeScaffold(
            topBar = {
                SearchComponent(
                    modifier = Modifier
                        .padding(all = AppTheme.spacing.spacingMedium),
                    value = "",
                    onValueChange = { },
                )
            },
        ) { }
    }
}
