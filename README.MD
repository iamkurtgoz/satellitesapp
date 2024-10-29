<h1 align="center">Satellites</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/iamkurtgoz/satellitesapp/actions"><img alt="Build Status" src="https://github.com/iamkurtgoz/satellitesapp/workflows/Android%20Build%20Check/badge.svg"/></a> <br>
</p>

<p align="center">
🗡️ Satellites demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack (Room, ViewModel) and Compose based on MVI architecture.
</p>

<p align="center">
    <img src="/previews/ss_first.png" height="800"/>
    <img src="/previews/ss_second.png" height="800"/>
</p>


## Download
Go to the [Releases](https://github.com/iamkurtgoz/satellitesapp/releases) to download the latest APK.

## Tech stack & Open-source libraries
- Minimum SDK level 24.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
    - Jetpack Compose: Android’s modern toolkit for declarative UI development.
    - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
    - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
    - Navigation: Facilitates screen navigation, complemented by [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.
    - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
    - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
    - MVI Architecture (Model - View - Intent): The MVI (Model-View-Intent) architecture treats user interactions or events as "Intents," which then lead to a state change observed by the "View." This approach centralizes all application state in one place, allowing for a single source of truth. MVI is particularly beneficial for managing complex user interactions, as it promotes consistent and understandable flows, improves testability, and simplifies debugging.
- [Gson](https://github.com/google/gson): Converts from json to object and from object to json.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format reflectionless serialization.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Turbine](https://github.com/cashapp/turbine): A small testing library for kotlinx.coroutines Flow.

### Module Graph

```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {"primaryTextColor":"#fff","primaryColor":"#5a4f7c","primaryBorderColor":"#5a4f7c","lineColor":"#f5a623","tertiaryColor":"#40375c","fontSize":"12px"}
  }
}%%

graph LR
  subgraph :core
    :core:navigation["navigation"]
    :core:dummy["dummy"]
    :core:local["local"]
    :core:common["common"]
    :core:common-ui["common-ui"]
    :core:designsystem["designsystem"]
    :core:resources["resources"]
  end
  subgraph :feature
    :feature:list["list"]
    :feature:detail["detail"]
  end
  :core:navigation --> :domain
  :core:dummy --> :data
  :core:local --> :data
  :feature:list --> :core:common
  :feature:list --> :core:common-ui
  :feature:list --> :core:navigation
  :feature:list --> :domain
  :feature:list --> :core:designsystem
  :feature:list --> :core:resources
  :feature:detail --> :core:common
  :feature:detail --> :core:common-ui
  :feature:detail --> :core:navigation
  :feature:detail --> :domain
  :feature:detail --> :core:designsystem
  :feature:detail --> :core:resources
  :core:resources --> :core:common
  :core:common-ui --> :core:designsystem
  :core:common-ui --> :domain
  :core:common-ui --> :core:common
  :core:common-ui --> :core:resources
  :app --> :data
  :app --> :domain
  :app --> :core:designsystem
  :app --> :core:common
  :app --> :core:common-ui
  :app --> :core:resources
  :app --> :core:navigation
  :app --> :core:local
  :app --> :core:dummy
  :app --> :feature:list
  :app --> :feature:detail
  :data --> :domain
  :data --> :core:common
  :data --> :core:resources
  :domain --> :core:common
  :core:designsystem --> :domain
```
## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/iamkurtgoz/satellitesapp/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/iamkurtgoz)__ on GitHub for my next creations! 🤩

# License
```xml
    Copyright 2024 Mehmet KURTGOZ

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
