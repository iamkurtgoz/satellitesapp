import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    `kotlin-dsl`
}

group = defaultSecrets().getProperty("BUILD_LOGIC_GROUP")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
        //BASE
        register("androidApplication") {
            id = "com.iamkurtgoz.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "com.iamkurtgoz.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.iamkurtgoz.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.iamkurtgoz.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryDesignSystemCompose") {
            id = "com.iamkurtgoz.android.library.design.system.compose"
            implementationClass = "AndroidLibraryComposeDesignSystemConventionPlugin"
        }
        register("androidFeature") {
            id = "com.iamkurtgoz.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        //SUB
        register("androidHilt") {
            id = "com.iamkurtgoz.android.sub.hilt"
            implementationClass = "SubAndroidHiltConventionPlugin"
        }
        register("androidKtlint") {
            id = "com.iamkurtgoz.sub.ktlint"
            implementationClass = "SubAndroidKtlintConventionPlugin"
        }
        register("androidSpotless") {
            id = "com.iamkurtgoz.sub.spotless"
            implementationClass = "SubAndroidSpotlessConventionPlugin"
        }
        register("androidDetekt") {
            id = "com.iamkurtgoz.sub.detekt"
            implementationClass = "SubAndroidDetektConventionPlugin"
        }
    }
}

//Secrets
fun defaultSecrets(): Properties {
    val keystoreFile = project.rootProject.file("../secrets.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())
    return properties
}
