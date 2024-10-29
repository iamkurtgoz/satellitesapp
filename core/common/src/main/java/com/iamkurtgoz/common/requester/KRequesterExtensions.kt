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

import com.iamkurtgoz.common.model.RestResult
import com.iamkurtgoz.common.requester.builder.KRequesterBuilder
import com.iamkurtgoz.common.requester.builder.KRequesterFlowBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

// Any
fun <T> Flow<T>.createRequesterFlowBuilder(scope: CoroutineScope, useWaitMillisecond: Boolean = true): KRequesterBuilder<T> {
    return KRequesterBuilder(scope = scope, request = this, useWaitMillisecond = useWaitMillisecond)
}

// Flow
fun <T> Flow<RestResult<T>>.createRequesterFlowBuilder(scope: CoroutineScope, useWaitMillisecond: Boolean = true): KRequesterFlowBuilder<T> {
    return KRequesterFlowBuilder(scope = scope, request = this, useWaitMillisecond = useWaitMillisecond)
}
