import com.iamkurtgoz.app.utils.implementation
import com.iamkurtgoz.app.utils.ksp
import com.iamkurtgoz.app.utils.kspAndroidTest
import com.iamkurtgoz.app.utils.kspTest
import com.iamkurtgoz.app.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SubAndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(libs.findLibrary("hilt.android").get())
                ksp(libs.findLibrary("hilt.compiler").get())
                kspAndroidTest(libs.findLibrary("hilt.compiler").get())
                kspTest(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
