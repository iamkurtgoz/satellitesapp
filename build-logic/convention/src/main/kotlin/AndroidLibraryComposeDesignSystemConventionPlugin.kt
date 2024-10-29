import com.android.build.gradle.LibraryExtension
import com.iamkurtgoz.app.extensions.configureAndroidCompose
import com.iamkurtgoz.app.utils.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AndroidLibraryComposeDesignSystemConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("kotlin-kapt")
            }

            pluginManager.apply("com.android.library")
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}
