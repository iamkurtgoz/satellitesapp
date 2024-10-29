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
package com.iamkurtgoz.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeSurface
import com.iamkurtgoz.navigation.DetailScreenRoute
import com.iamkurtgoz.resources.R as resourceR

@Composable
internal fun DetailScreenContent(
    state: DetailScreenContract.State,
    modifier: Modifier = Modifier,
    setEvent: (DetailScreenContract.Event) -> Unit,
) {
    state.satelliteDetailModel?.let { safeSatelliteDetailModel ->
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = state.route.name,
                style = AppTheme.typography.displaySm.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = safeSatelliteDetailModel.firstFlightDate,
                style = AppTheme.typography.textSm.copy(
                    fontWeight = FontWeight.Light,
                ),
                color = AppTheme.colors.foregroundSecondary,
                modifier = Modifier
                    .padding(top = AppTheme.spacing.spacingMedium),
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                        block = {
                            append(stringResource(resourceR.string.height_mass))
                        },
                    )

                    append(DetailScreenContract.Static.DOUBLE_DOT)

                    append("${safeSatelliteDetailModel.height}/${safeSatelliteDetailModel.mass}")
                },
                modifier = Modifier
                    .padding(top = AppTheme.spacing.spacingHuge),
                style = AppTheme.typography.textSm.copy(
                    fontWeight = FontWeight.Light,
                ),
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                        block = {
                            append(stringResource(resourceR.string.cost))
                        },
                    )

                    append(DetailScreenContract.Static.DOUBLE_DOT)

                    append(safeSatelliteDetailModel.costPerLaunch.toString())
                },
                modifier = Modifier
                    .padding(top = AppTheme.spacing.spacingMedium),
                style = AppTheme.typography.textSm.copy(
                    fontWeight = FontWeight.Light,
                ),
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                        block = {
                            append(stringResource(resourceR.string.last_position))
                        },
                    )

                    append(DetailScreenContract.Static.DOUBLE_DOT)

                    append("(0.864328522,0.646450855)")
                },
                modifier = Modifier
                    .padding(top = AppTheme.spacing.spacingMedium),
                style = AppTheme.typography.textSm.copy(
                    fontWeight = FontWeight.Light,
                ),
            )
        }
    }
}

@AppWithNightModePreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeSurface {
            DetailScreenContent(
                state = DetailScreenContract.State(
                    isLoading = false,
                    route = DetailScreenRoute(id = 1, name = "Starship-1"),
                ),
                setEvent = { },
            )
        }
    }
}
