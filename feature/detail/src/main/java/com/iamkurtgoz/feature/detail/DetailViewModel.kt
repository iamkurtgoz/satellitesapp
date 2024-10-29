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

import com.iamkurtgoz.common.core.CoreViewModel
import com.iamkurtgoz.domain.usecase.impl.SatelliteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val satelliteListUseCase: SatelliteListUseCase,
) : CoreViewModel<DetailScreenContract.State, DetailScreenContract.SideEffect, DetailScreenContract.Event>(
    initialState = DetailScreenContract.State(
        isLoading = false,
    ),
) {
    init {
        if (!state.value.isInitialize) {
            updateState { it.copy(isInitialize = true) }
        }
    }

    override fun setEvent(event: DetailScreenContract.Event) {
        when (event) {
            is DetailScreenContract.Event.PopBackStack -> {
                setPopBackStackSideEffect()
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        updateState { it.copy(isLoading = isLoading) }
    }

    private fun setPopBackStackSideEffect() {
        setSideEffect(DetailScreenContract.SideEffect.PopBackStack)
    }
}
