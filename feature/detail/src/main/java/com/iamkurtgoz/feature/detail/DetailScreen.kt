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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iamkurtgoz.commonui.component.AppToolbar
import com.iamkurtgoz.commonui.component.AppToolbarFields
import com.iamkurtgoz.commonui.component.ErrorComponent
import com.iamkurtgoz.commonui.extension.observeSideEffect
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.designsystem.theme.AppThemeSurface
import com.iamkurtgoz.navigation.DetailScreenRoute
import com.iamkurtgoz.resources.R as resourceR

@Composable
internal fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.observeSideEffect {
        when (it) {
            is DetailScreenContract.SideEffect.PopBackStack -> {
                popBackStack.invoke()
            }
        }
    }

    Scaffold(
        state = state,
        setEvent = viewModel::setEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Scaffold(
    state: DetailScreenContract.State,
    setEvent: (DetailScreenContract.Event) -> Unit,
) {
    AppThemeScaffold(
        topBar = {
            AppToolbar.Toolbar(
                modifier = Modifier,
                leftContent = {
                    AppToolbarFields.NavigateIcon {
                        setEvent.invoke(DetailScreenContract.Event.PopBackStack)
                    }
                },
                centerContent = {
                    AppToolbarFields.TitleWithSubTitle(
                        text = stringResource(resourceR.string.detail_screen_title),
                        subText = state.route.name,
                    )
                },
            )
        },
    ) { padding ->
        DetailScreenContent(
            modifier = Modifier
                .padding(padding),
            state = state,
            setEvent = setEvent,
        )
    }

    AnimatedVisibility(
        visible = state.error != null,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        state.error?.message?.let { message ->
            if (message.isEmpty()) {
                ErrorComponent.Primary(modifier = Modifier.fillMaxSize()) {
                    setEvent.invoke(DetailScreenContract.Event.Reload)
                }
            } else {
                ErrorComponent.Secondary(
                    modifier = Modifier
                        .fillMaxSize(),
                    message = message,
                ) {
                    setEvent.invoke(DetailScreenContract.Event.Reload)
                }
            }
        }
    }

    AnimatedVisibility(
        visible = state.isLoading,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                color = AppTheme.colors.foregroundPrimary,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            )
        }
    }
}

@AppWithNightModePreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeSurface {
            Scaffold(
                state = DetailScreenContract.State(
                    isLoading = false,
                    route = DetailScreenRoute(id = 1, name = "Starship-1"),
                ),
                setEvent = { },
            )
        }
    }
}
