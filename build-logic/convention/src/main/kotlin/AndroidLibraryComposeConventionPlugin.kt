import com.iamkurtgoz.app.utils.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.iamkurtgoz.android.library.design.system.compose")
            }

            dependencies {
                //Projects
                implementation(project(":core:designsystem"))
            }
        }
    }
}
