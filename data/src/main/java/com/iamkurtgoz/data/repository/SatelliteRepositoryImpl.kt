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
package com.iamkurtgoz.data.repository

import com.iamkurtgoz.common.model.RestResult
import com.iamkurtgoz.common.model.mapOnSuccess
import com.iamkurtgoz.data.core.CoreRepository
import com.iamkurtgoz.data.dataSource.LocalDataSource
import com.iamkurtgoz.data.dataSource.RemoteDataSource
import com.iamkurtgoz.data.di.DummyRemoteDataSource
import com.iamkurtgoz.data.mapper.toUIModel
import com.iamkurtgoz.domain.model.SatelliteDetailUIModel
import com.iamkurtgoz.domain.model.SatellitePositionUIModel
import com.iamkurtgoz.domain.model.SatelliteUIModel
import com.iamkurtgoz.domain.repository.SatelliteRepository
import javax.inject.Inject

internal class SatelliteRepositoryImpl @Inject constructor(
    @DummyRemoteDataSource private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : SatelliteRepository, CoreRepository() {
    override suspend fun fetchSatellites(): RestResult<List<SatelliteUIModel>> {
        return requestAny { remoteDataSource.fetchSatellites() }
            .mapOnSuccess { response ->
                response.map { it.toUIModel() }
            }
    }

    override suspend fun fetchSatelliteDetail(id: Int): RestResult<SatelliteDetailUIModel?> {
        val cache = localDataSource.fetch(id = id)
        return cache?.let {
            requestAny { cache }
                .mapOnSuccess { response ->
                    response.toUIModel()
                }
        } ?: run {
            requestAny { remoteDataSource.fetchSatelliteDetail(id = id) }
                .mapOnSuccess { response ->
                    response?.let {
                        localDataSource.insert(it)
                    }
                    response?.toUIModel()
                }
        }
    }

    override suspend fun fetchSatellitePositions(id: Int): List<SatellitePositionUIModel> {
        val positions = remoteDataSource.fetchSatellitePositions(id)
        return positions?.toUIModel() ?: emptyList()
    }
}
