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

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iamkurtgoz.data.dataSource.RemoteDataSource
import com.iamkurtgoz.data.model.SatelliteResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DummyRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) : RemoteDataSource {

    override suspend fun fetchSatellites(): List<SatelliteResponse> {
        val type = object : TypeToken<List<SatelliteResponse>>() {}.type
        val data = readJsonFromAssetsAsync(context, SATELLITES_FILE_NAME)
        return gson.fromJson(data, type)
    }

    companion object {
        private const val SATELLITES_FILE_NAME = "satellites.json"
        private const val SATELLITE_DETAIL_FILE_NAME = "satellite_detail.json"
        private const val POSITIONS_FILE_NAME = "positions.json"

        private suspend fun readJsonFromAssetsAsync(context: Context, fileName: String): String {
            return withContext(Dispatchers.IO) {
                context.assets.open(fileName).bufferedReader().use { it.readText() }
            }
        }
    }
}
