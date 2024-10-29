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
package com.iamkurtgoz.domain.usecase.impl

import com.iamkurtgoz.common.di.IoDispatcher
import com.iamkurtgoz.common.model.RestResult
import com.iamkurtgoz.domain.core.CoreUseCase
import com.iamkurtgoz.domain.model.SatelliteUIModel
import com.iamkurtgoz.domain.repository.SatelliteRepository
import com.iamkurtgoz.domain.usecase.domain.IUseCaseWithoutParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteListUseCase @Inject constructor(
    private val repository: SatelliteRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : IUseCaseWithoutParams<RestResult<List<SatelliteUIModel>>>, CoreUseCase(coroutineDispatcher) {
    override fun invoke(): Flow<RestResult<List<SatelliteUIModel>>> = prepare {
        repository.fetchSatellites()
    }
}
