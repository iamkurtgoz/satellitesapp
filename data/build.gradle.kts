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
plugins {
    id("com.iamkurtgoz.android.library")
    id("com.iamkurtgoz.android.sub.hilt")
}

android {
    namespace = "com.iamkurtgoz.data"
    hilt.enableAggregatingTask = true
}

dependencies {
    // Projects
    implementation(projects.domain)
    implementation(projects.core.common)
    implementation(projects.core.resources)

    // AndroidX
    implementation(libs.androidx.core.ktx)

    // Network
    implementation(libs.network.gson)

    // Local
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
