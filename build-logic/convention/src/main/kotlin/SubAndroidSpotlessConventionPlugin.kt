import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SubAndroidSpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.diffplug.spotless")
            }

            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt")
                    licenseHeaderFile(
                        rootProject.file("$rootDir/spotless/copyright.kt"),
                        "^(package|object|import|interface)",
                    )
                }

                kotlinGradle {
                    target("**/*.gradle.kts")
                    targetExclude("**/build/**/*.kts")
                    licenseHeaderFile(
                        rootProject.file("$rootDir/spotless/copyright.kts"),
                        "(^(?![\\/ ]\\*).*\$)",
                    )
                }

                format("xml") {
                    target("**/*.xml")
                    targetExclude("**/build/**/*.xml")
                    licenseHeaderFile(
                        rootProject.file("$rootDir/spotless/copyright.xml"),
                        "(<[^!?])",
                    )
                }
            }
        }
    }
}

