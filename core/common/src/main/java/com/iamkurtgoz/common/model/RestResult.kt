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
package com.iamkurtgoz.common.model

sealed class RestResult<out T> {

    class Success<out T>(
        val result: T,
        val totalCount: Int? = null,
        val filterCount: Int? = null,
        val hasNext: Boolean? = null,
    ) : RestResult<T>()

    class Error(
        val error: BaseError,
    ) : RestResult<Nothing>()

    data class Progress(val progress: Int) : RestResult<Nothing>()
    data object Loading : RestResult<Nothing>()
}

inline fun <T> RestResult<T>.onSuccess(action: (T) -> Unit): RestResult<T> {
    if (this is RestResult.Success) action(result)
    return this
}

inline fun <T> RestResult<T>.onError(action: (exc: BaseError) -> Unit): RestResult<T> {
    if (this is RestResult.Error) action(error)
    return this
}

inline fun <T> RestResult<T>.onProgress(action: (progress: Int) -> Unit): RestResult<T> {
    if (this is RestResult.Progress) action(progress)
    return this
}

inline fun <T> RestResult<T>.onLoading(action: () -> Unit): RestResult<T> {
    if (this is RestResult.Loading) action()
    return this
}

inline fun <T, R> RestResult<T>.mapOnSuccess(map: (T) -> R): RestResult<R> = when (this) {
    is RestResult.Success -> RestResult.Success(map(result), totalCount, filterCount)
    is RestResult.Error -> this
    is RestResult.Progress -> this
    is RestResult.Loading -> this
}
