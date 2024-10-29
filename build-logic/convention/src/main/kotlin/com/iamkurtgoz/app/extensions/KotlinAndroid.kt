package com.iamkurtgoz.app.extensions

import com.android.build.api.dsl.CommonExtension
import com.iamkurtgoz.app.utils.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = rootProject.defaultSecrets().getProperty("COMPILE_SDK").toInt()

        defaultConfig {
            minSdk = rootProject.defaultSecrets().getProperty("MIN_SDK").toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                val warningsAsErrors: String? by project
                allWarningsAsErrors.set(warningsAsErrors.toBoolean())
                jvmTarget.set(JvmTarget.JVM_17)

                freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            }
        }

    }
    configureKotlin()
    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configureKotlin()
}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() {
    with(extensions.getByType<KotlinAndroidProjectExtension>()) {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)

            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.FlowPreview")
        }
    }
}

internal fun Project.defaultSecrets(): Properties {
    val keystoreFile = this.file("secrets.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())
    return properties
}