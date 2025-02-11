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

import androidx.compose.runtime.Immutable
import com.iamkurtgoz.common.core.CoreState
import com.iamkurtgoz.common.model.BaseError
import com.iamkurtgoz.domain.model.SatelliteDetailUIModel
import com.iamkurtgoz.domain.model.SatellitePositionUIModel
import com.iamkurtgoz.navigation.DetailScreenRoute

internal class DetailScreenContract {
    @Immutable
    data class State(
        override val isLoading: Boolean,
        val route: DetailScreenRoute,
        val isInitialize: Boolean = false,
        val satelliteDetailModel: SatelliteDetailUIModel? = null,
        var satellitePositionModel: SatellitePositionUIModel? = null,
        val error: BaseError? = null,
    ) : CoreState.ViewState

    sealed class SideEffect : CoreState.SideEffect {
        data object PopBackStack : SideEffect()
    }

    sealed class Event : CoreState.Event {
        data object PopBackStack : Event()
        data object Reload : Event()
    }

    object Static {
        const val DOUBLE_DOT: String = ":"
    }
}
