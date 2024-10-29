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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iamkurtgoz.designsystem.internal.AppWithNightModePreviews
import com.iamkurtgoz.designsystem.theme.AppTheme
import com.iamkurtgoz.designsystem.theme.AppThemeSurface
import com.iamkurtgoz.domain.model.SatelliteUIModel
import com.iamkurtgoz.list.component.ListItemRow
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ListScreenContent(
    state: ListScreenContract.State,
    satelliteList: ImmutableList<SatelliteUIModel>,
    modifier: Modifier = Modifier,
    setEvent: (ListScreenContract.Event) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (!state.isLoading && !state.isSearching) {
            itemsIndexed(
                items = satelliteList,
                key = { _, item ->
                    item.id
                },
            ) { index, item ->
                ListItemRow(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.spacing.spacingMedium)
                        .padding(top = if (index == ListScreenContract.Static.FIRST_INDEX) AppTheme.spacing.spacingMedium else AppTheme.spacing.spacingNone),
                    index = index,
                    isActive = item.active,
                    title = item.name,
                    onClickAction = {
                        val event = ListScreenContract.Event.NavigateToDetail(id = item.id, name = item.name)
                        setEvent.invoke(event)
                    },
                )
            }
        }
    }
}

@AppWithNightModePreviews
@Composable
private fun Preview() {
    AppTheme {
        AppThemeSurface {
            ListScreenContent(
                state = ListScreenContract.State(isLoading = true),
                satelliteList = persistentListOf(),
                setEvent = { },
            )
        }
    }
}
