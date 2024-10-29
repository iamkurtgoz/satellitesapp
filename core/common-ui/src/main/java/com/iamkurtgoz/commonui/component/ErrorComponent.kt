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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.resources.R as resourceR

object ErrorComponent {
    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        @StringRes title: Int = resourceR.string.error_title,
        @StringRes message: Int = resourceR.string.error_message,
        @StringRes buttonText: Int = resourceR.string.try_again_button,
        onClick: () -> Unit,
    ) = ErrorComponentImpl(
        modifier = modifier,
        title = stringResource(title),
        message = stringResource(message),
        buttonText = stringResource(buttonText),
        onClick = onClick,
    )

    @Composable
    fun Secondary(
        modifier: Modifier = Modifier,
        title: String = stringResource(resourceR.string.error_title),
        message: String = stringResource(resourceR.string.error_message),
        buttonText: String = stringResource(resourceR.string.try_again_button),
        onClick: () -> Unit,
    ) = ErrorComponentImpl(
        modifier = modifier,
        title = title,
        message = message,
        buttonText = buttonText,
        onClick = onClick,
    )
}

@Composable
private fun ErrorComponentImpl(
    title: String,
    message: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = AppTheme.typography.displayXs.copy(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .padding(horizontal = AppTheme.spacing.spacingMedium),
        )

        Text(
            text = message,
            style = AppTheme.typography.textLg.copy(
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = AppTheme.spacing.spacingMedium)
                .padding(top = AppTheme.spacing.spacingSmallest),
        )

        Button(
            onClick = onClick,
            content = {
                Text(buttonText)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.buttonBackground,
                contentColor = AppTheme.colors.buttonForeground,
            ),
            modifier = Modifier
                .padding(vertical = AppTheme.spacing.spacingMedium),
        )
    }
}

@AppWithNightModePreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeScaffold {
            ErrorComponent.Primary(
                modifier = Modifier
                    .fillMaxSize(),
                onClick = { },
            )
        }
    }
}
