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
package com.iamkurtgoz.local.di

import android.content.Context
import androidx.room.Room
import com.iamkurtgoz.data.dataSource.LocalDataSource
import com.iamkurtgoz.local.AppDatabase
import com.iamkurtgoz.local.dao.SatelliteDetailDao
import com.iamkurtgoz.local.dataSource.LocalDataSourceImpl
import com.iamkurtgoz.data.di.DatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class AppDatabaseModule {
    @DatabaseName
    @Provides
    fun provideDatabaseName() = "SatelliteDatabase"

    @Provides
    fun provideCoinLocalDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName,
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSatelliteDetailDao(db: AppDatabase) = db.getSatelliteDetailDao()

    @Provides
    fun provideLocalDataSource(satelliteDetailDao: SatelliteDetailDao): LocalDataSource = LocalDataSourceImpl(
        satelliteDetailDao = satelliteDetailDao,
    )
}
