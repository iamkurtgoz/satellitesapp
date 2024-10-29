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

import androidx.compose.runtime.Immutable
import com.iamkurtgoz.common.core.CoreState

internal class ListScreenContract {
    @Immutable
    data class State(
        override val isLoading: Boolean,
        val isInitialize: Boolean = false,
        val isSearching: Boolean = false,
    ) : CoreState.ViewState

    sealed class SideEffect : CoreState.SideEffect {
        data class NavigateToDetail(val id: Int) : SideEffect()
    }

    sealed class Event : CoreState.Event {
        data class UpdateSearchKeyTextState(val input: String) : Event()
        data class NavigateToDetail(val id: Int) : Event()
    }

    object Static {
        const val FIRST_INDEX: Int = 0
        const val SEARCH_DEBOUNCE: Long = 500L
        const val SEARCH_DELAY: Long = 500L
    }
}
