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
package com.iamkurtgoz.dummy.dataSource

import com.iamkurtgoz.data.dataSource.RemoteDataSource
import com.iamkurtgoz.data.model.SatelliteDetailResponse
import com.iamkurtgoz.data.model.SatelliteResponse
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor() : RemoteDataSource {

    override suspend fun fetchSatellites(): List<SatelliteResponse> {
        throw NotImplementedError()
    }

    override suspend fun fetchSatelliteDetail(id: Int): SatelliteDetailResponse? {
        throw NotImplementedError()
    }
}
