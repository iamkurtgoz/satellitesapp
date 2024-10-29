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
package com.iamkurtgoz.designsystem.internal

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Small phone",
    device = "spec:width=360dp,height=640dp,dpi=160",
)
@Preview(
    name = "Phone",
    device = "spec:width=411dp,height=891dp,dpi=420",
)
@Preview(
    name = "Foldable",
    device = "spec:width=673dp,height=841dp,dpi=420",
)
@Preview(
    name = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240",
)
@Preview(
    name = "Small phone",
    device = "spec:width=360dp,height=640dp,dpi=160",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Phone",
    device = "spec:width=411dp,height=891dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Foldable",
    device = "spec:width=673dp,height=841dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class AppPreviews

@Preview(
    name = "Phone",
    device = "spec:width=411dp,height=891dp,dpi=420",
)
@Preview(
    name = "Phone",
    device = "spec:width=411dp,height=891dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class AppWithNightModePreviews
