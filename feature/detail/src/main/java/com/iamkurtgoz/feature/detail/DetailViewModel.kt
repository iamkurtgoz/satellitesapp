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

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.iamkurtgoz.common.core.CoreViewModel
import com.iamkurtgoz.common.model.BaseError
import com.iamkurtgoz.domain.usecase.impl.SatelliteDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val satelliteDetailUseCase: SatelliteDetailUseCase,
) : CoreViewModel<DetailScreenContract.State, DetailScreenContract.SideEffect, DetailScreenContract.Event>(
    initialState = DetailScreenContract.State(
        isLoading = false,
        route = savedStateHandle.toRoute(),
    ),
) {
    init {
        if (!state.value.isInitialize) {
            updateState { it.copy(isInitialize = true) }
            fetchDetail()
        }
    }

    override fun setEvent(event: DetailScreenContract.Event) {
        when (event) {
            is DetailScreenContract.Event.PopBackStack -> {
                setPopBackStackSideEffect()
            }
            is DetailScreenContract.Event.Reload -> {
                reload()
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        updateState { it.copy(isLoading = isLoading) }
    }

    private fun setPopBackStackSideEffect() {
        setSideEffect(DetailScreenContract.SideEffect.PopBackStack)
    }

    private fun reload() {
        updateState { it.copy(error = null) }
        fetchDetail()
    }

    private fun fetchDetail() {
        satelliteDetailUseCase
            .invoke(state.value.route.id)
            .requester
            .onLoading {
                setLoading(true)
            }
            .onError { error ->
                setLoading(false)
                updateState { it.copy(error = error) }
            }
            .callWithSuccess { response ->
                response?.let {
                    updateState { it.copy(satelliteDetailModel = response) }
                } ?: run {
                    updateState { it.copy(error = BaseError()) }
                }
                setLoading(false)
            }
    }
}
