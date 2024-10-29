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
package com.iamkurtgoz.common.requester

import com.iamkurtgoz.common.model.BaseError
import com.iamkurtgoz.common.model.RestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<RestResult<T>>.onSuccess(action: suspend (T, totalCount: Int?, filteredCount: Int?) -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Success) {
            action.invoke(restResult.result, restResult.totalCount, restResult.filterCount)
        }
        emit(restResult)
    }
}

fun <T> Flow<RestResult<T>>.onError(action: suspend (BaseError) -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Error) {
            action.invoke(restResult.error)
        }
        emit(restResult)
    }
}

fun <T> Flow<RestResult<T>>.onLoading(action: suspend () -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Loading) {
            action.invoke()
        }
        emit(restResult)
    }
}
