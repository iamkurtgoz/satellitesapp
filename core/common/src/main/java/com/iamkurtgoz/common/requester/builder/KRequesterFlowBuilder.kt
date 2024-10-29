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

import com.iamkurtgoz.common.model.BaseError
import com.iamkurtgoz.common.model.RestResult
import com.iamkurtgoz.common.requester.onError
import com.iamkurtgoz.common.requester.onLoading
import com.iamkurtgoz.common.requester.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class KRequesterFlowBuilder<T>(
    private val scope: CoroutineScope,
    private val request: Flow<RestResult<T>>,
    private val useWaitMillisecond: Boolean,
) {
    private var onLoading: (suspend () -> Unit)? = null
    private var onError: (suspend (BaseError) -> Unit)? = null
    private var onSuccess: (suspend (T) -> Unit)? = null
    private var onSuccessPagination: (suspend (T, listableSize: Int?) -> Unit)? = null

    fun onLoading(onLoading: suspend () -> Unit): KRequesterFlowBuilder<T> {
        this.onLoading = onLoading
        return this
    }

    fun onError(onError: suspend (BaseError) -> Unit): KRequesterFlowBuilder<T> {
        this.onError = onError
        return this
    }

    fun callWithSuccess(onSuccess: suspend (T) -> Unit) = scope.launch {
        this@KRequesterFlowBuilder.onSuccess = onSuccess
        call()
    }

    fun callWithSuccessPagination(onSuccessPagination: suspend (T, listableSize: Int?) -> Unit) = scope.launch {
        this@KRequesterFlowBuilder.onSuccessPagination = onSuccessPagination
        call()
    }

    fun call() = scope.launch {
        request
            .onLoading {
                onLoading?.invoke()
            }
            .onError {
                delay(if (useWaitMillisecond) REQUEST_RESPONSE_WAIT_MILLISECOND else 0)
                onError?.invoke(it)
            }
            .onSuccess { result, _, filteredCount ->
                delay(if (useWaitMillisecond) REQUEST_RESPONSE_WAIT_MILLISECOND else 0)
                onSuccess?.invoke(result)
                onSuccessPagination?.invoke(result, filteredCount)
            }
            .launchIn(this)
    }

    companion object {
        private const val ONE_SECOND = 1000L
        private const val REQUEST_RESPONSE_WAIT_MILLISECOND = (ONE_SECOND * 0.5).toLong()
    }
}
