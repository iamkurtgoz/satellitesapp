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
package com.iamkurtgoz.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iamkurtgoz.commonui.component.ErrorComponent
import com.iamkurtgoz.commonui.component.SearchComponent
import com.iamkurtgoz.commonui.extension.observeSideEffect
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeScaffold
import com.iamkurtgoz.designsystem.theme.AppThemeSurface
import com.iamkurtgoz.domain.model.SatelliteUIModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    navigateToDetail: (Int, String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val satelliteList by viewModel.satelliteList.collectAsStateWithLifecycle()
    val searchKeyTextState by viewModel.searchKeyTextState.collectAsStateWithLifecycle()

    viewModel.sideEffect.observeSideEffect {
        when (it) {
            is ListScreenContract.SideEffect.NavigateToDetail -> {
                navigateToDetail.invoke(it.id, it.name)
            }
        }
    }

    Scaffold(
        state = state,
        satelliteList = satelliteList,
        searchKeyTextState = searchKeyTextState,
        setEvent = viewModel::setEvent,
    )
}

@Composable
private fun Scaffold(
    state: ListScreenContract.State,
    satelliteList: ImmutableList<SatelliteUIModel>,
    searchKeyTextState: String,
    setEvent: (ListScreenContract.Event) -> Unit,
) {
    val topPadding = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    AppThemeScaffold(
        topBar = {
            SearchComponent(
                modifier = Modifier
                    .padding(top = topPadding)
                    .padding(horizontal = AppTheme.spacing.spacingMedium),
                value = searchKeyTextState,
                onValueChange = {
                    setEvent.invoke(ListScreenContract.Event.UpdateSearchKeyTextState(it))
                },
            )
        },
    ) { padding ->
        ListScreenContent(
            modifier = Modifier
                .padding(padding),
            state = state,
            satelliteList = satelliteList,
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
                    setEvent.invoke(ListScreenContract.Event.Reload)
                }
            } else {
                ErrorComponent.Secondary(
                    modifier = Modifier
                        .fillMaxSize(),
                    message = message,
                ) {
                    setEvent.invoke(ListScreenContract.Event.Reload)
                }
            }
        }
    }

    AnimatedVisibility(
        visible = state.isLoading || state.isSearching,
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
                state = ListScreenContract.State(isLoading = false),
                satelliteList = persistentListOf(),
                searchKeyTextState = "",
                setEvent = { },
            )
        }
    }
}
