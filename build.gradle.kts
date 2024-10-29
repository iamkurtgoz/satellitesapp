import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
//import dev.iurysouza.modulegraph.Theme
import java.util.Properties

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.roborazzi) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.spotless)
    //alias(libs.plugins.dev.iurysouza.modulegraph)
}

subprojects {
    project.plugins.applyBaseConfig(project)
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
            }

            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        libraryConfig()
                    }
            }
        }
    }
}

fun LibraryExtension.libraryConfig() {
    buildFeatures.buildConfig = true
    flavorDimensions += "version"
    productFlavors {
        create("beta") {
            dimension = "version"
        }
        create("prod") {
            dimension = "version"
        }
    }
}

//Secrets
fun defaultSecrets(): Properties {
    val keystoreFile = project.rootProject.file("secrets.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())
    return properties
}
/*

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "### Module Graph"
    theme.set(
        Theme.BASE(
            mapOf(
                "primaryTextColor" to "#fff",
                "primaryColor" to "#5a4f7c",
                "primaryBorderColor" to "#5a4f7c",
                "lineColor" to "#f5a623",
                "tertiaryColor" to "#40375c",
                "fontSize" to "12px",
            ),
            focusColor = "#FA8140"
        ),
    )
}

 */
