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
package com.iamkurtgoz.domain.core

import com.iamkurtgoz.common.di.IoDispatcher
import com.iamkurtgoz.common.extensions.toBaseError
import com.iamkurtgoz.common.model.RestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

open class CoreUseCase(@IoDispatcher val coroutineDispatcher: CoroutineDispatcher) {
    inline fun <reified T : Any> prepare(
        crossinline block: suspend () -> RestResult<T>,
    ): Flow<RestResult<T>> = flow {
        this.emit(block.invoke())
    }.onStart {
        emit(RestResult.Loading)
    }.catch { throwable ->
        emit(RestResult.Error(throwable.toBaseError()))
    }.flowOn(coroutineDispatcher)

    inline fun <reified T : Any> prepareNullable(
        crossinline block: suspend () -> RestResult<T?>,
    ): Flow<RestResult<T?>> = flow {
        this.emit(block.invoke())
    }.onStart {
        emit(RestResult.Loading)
    }.catch { throwable ->
        emit(RestResult.Error(throwable.toBaseError()))
    }.flowOn(coroutineDispatcher)
}
