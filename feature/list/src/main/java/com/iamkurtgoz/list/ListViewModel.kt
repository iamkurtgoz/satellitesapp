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

import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.common.core.CoreViewModel
import com.iamkurtgoz.domain.model.SatelliteUIModel
import com.iamkurtgoz.domain.usecase.impl.SatelliteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(
    private val satelliteListUseCase: SatelliteListUseCase,
) : CoreViewModel<ListScreenContract.State, ListScreenContract.SideEffect, ListScreenContract.Event>(
    initialState = ListScreenContract.State(
        isLoading = false,
    ),
) {
    private val _searchKeyTextState = MutableStateFlow("")
    val searchKeyTextState = _searchKeyTextState.asStateFlow()

    private val _satelliteList = MutableStateFlow(persistentListOf<SatelliteUIModel>())
    val satelliteList = searchKeyTextState
        .onEach {
            updateIsSearching(isSearching = true)
        }
        .debounce(ListScreenContract.Static.SEARCH_DEBOUNCE)
        .combine(_satelliteList) { text, list ->
            if (text.isBlank()) {
                return@combine list
                    .toPersistentList()
            } else {
                delay(ListScreenContract.Static.SEARCH_DELAY)
                list.filter {
                    it.doesMatchSearchQuery(text)
                }.toPersistentList()
            }
        }
        .onEach {
            updateIsSearching(isSearching = false)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _satelliteList.value,
        )

    init {
        if (!state.value.isInitialize) {
            updateState { it.copy(isInitialize = true) }
            fetchList()
        }
    }

    override fun setEvent(event: ListScreenContract.Event) {
        when (event) {
            is ListScreenContract.Event.UpdateSearchKeyTextState -> {
                updateSearchKeyTextState(event.input)
            }
            is ListScreenContract.Event.NavigateToDetail -> {
                navigateToDetail(event.id, event.name)
            }
            is ListScreenContract.Event.Reload -> {
                reload()
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        updateState { it.copy(isLoading = isLoading) }
    }

    private fun updateSearchKeyTextState(input: String) {
        _searchKeyTextState.value = input
    }

    private fun navigateToDetail(id: Int, name: String) {
        if (_satelliteList.value.any { it.id == id }) {
            setSideEffect(ListScreenContract.SideEffect.NavigateToDetail(id = id, name = name))
        }
    }

    private fun reload() {
        updateState { it.copy(error = null) }
        fetchList()
    }

    private fun updateIsSearching(isSearching: Boolean) {
        updateState { it.copy(isSearching = isSearching) }
    }

    private fun fetchList() {
        satelliteListUseCase
            .invoke()
            .requester
            .onLoading {
                setLoading(true)
            }
            .onError { error ->
                setLoading(false)
                updateState { it.copy(error = error) }
            }
            .callWithSuccess { response ->
                _satelliteList.value = response.toPersistentList()
                setLoading(false)
            }
    }
}
