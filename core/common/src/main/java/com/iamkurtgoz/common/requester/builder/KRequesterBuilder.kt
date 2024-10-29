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
package com.iamkurtgoz.common.requester.builder

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class KRequesterBuilder<T>(
    private val scope: CoroutineScope,
    private val request: Flow<T>,
    private val useWaitMillisecond: Boolean,
) {
    private var onLoading: (suspend () -> Unit)? = null
    private var onError: (suspend (Throwable) -> Unit)? = null
    private var onSuccess: (suspend (T) -> Unit)? = null

    fun onLoading(onLoading: suspend () -> Unit): KRequesterBuilder<T> {
        this.onLoading = onLoading
        return this
    }

    fun onError(onError: suspend (Throwable) -> Unit): KRequesterBuilder<T> {
        this.onError = onError
        return this
    }

    fun callWithSuccess(onSuccess: suspend (T) -> Unit) = scope.launch {
        this@KRequesterBuilder.onSuccess = onSuccess
        call()
    }

    private fun call() = scope.launch {
        request
            .onStart {
                onLoading?.invoke()
            }
            .catch {
                delay(if (useWaitMillisecond) REQUEST_RESPONSE_WAIT_MILLISECOND else 0)
                onError?.invoke(it)
            }
            .onEach {
                delay(if (useWaitMillisecond) REQUEST_RESPONSE_WAIT_MILLISECOND else 0)
                onSuccess?.invoke(it)
            }
            .launchIn(this)
    }

    companion object {
        private const val ONE_SECOND = 1000L
        private const val REQUEST_RESPONSE_WAIT_MILLISECOND = (ONE_SECOND * 0.5).toLong()
    }
}
