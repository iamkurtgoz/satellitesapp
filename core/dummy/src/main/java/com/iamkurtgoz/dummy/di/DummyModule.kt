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
package com.iamkurtgoz.dummy.di

import com.iamkurtgoz.data.dataSource.RemoteDataSource
import com.iamkurtgoz.data.di.DummyRemoteDataSource
import com.iamkurtgoz.data.di.RealRemoteDataSource
import com.iamkurtgoz.dummy.dataSource.DummyRemoteDataSourceImpl
import com.iamkurtgoz.dummy.dataSource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DummyModule {

    @Binds
    @DummyRemoteDataSource
    fun bindDummyRemoteDataSource(impl: DummyRemoteDataSourceImpl): RemoteDataSource

    @Binds
    @RealRemoteDataSource
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}
