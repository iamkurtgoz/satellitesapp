package com.iamkurtgoz.app.extensions

import com.android.build.api.dsl.CommonExtension
import com.iamkurtgoz.app.utils.buildComposeMetricsParameters
import com.iamkurtgoz.app.utils.kapt
import com.iamkurtgoz.app.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {

    commonExtension.apply {
        buildFeatures.compose = true

        dependencies {
            //Compose
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            //Lifecycle
            add("implementation", libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewModelCompose").get())
            kapt(libs.findLibrary("androidx-lifecycle-compiler").get())

            // Add ComponentActivity to debug manifest
            add("debugImplementation", libs.findLibrary("androidx.compose.ui.testManifest").get())
            // Screenshot Tests on JVM
            add("testImplementation", libs.findLibrary("robolectric").get())
            add("testImplementation", libs.findLibrary("roborazzi").get())
        }
    }

    with(extensions.getByType<KotlinAndroidProjectExtension>()) {
        compilerOptions {
            buildComposeMetricsParameters().forEach {
                freeCompilerArgs.add(it)
            }
        }
    }
}
