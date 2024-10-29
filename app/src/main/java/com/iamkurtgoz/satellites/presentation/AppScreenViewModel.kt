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
package com.iamkurtgoz.satellites.presentation

import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.common.core.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AppScreenViewModel @Inject constructor() : CoreViewModel<AppScreenContract.State, AppScreenContract.SideEffect, AppScreenContract.Event>(
    initialState = AppScreenContract.State(
        isLoading = false,
    ),
) {

    init {
        viewModelScope.launch {
            delay(AppScreenContract.Static.KEEP_SPLASH_SCREEN_DELAY)
            updateState { it.copy(keepSplashScreenOn = false) }
        }
    }

    override fun setEvent(event: AppScreenContract.Event) { }
}
