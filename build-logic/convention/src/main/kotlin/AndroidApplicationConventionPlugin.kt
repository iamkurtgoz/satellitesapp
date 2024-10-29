import com.android.build.api.dsl.ApplicationExtension
import com.iamkurtgoz.app.extensions.configureKotlinAndroid
import com.iamkurtgoz.app.extensions.defaultSecrets
import com.iamkurtgoz.app.extensions.registerPrePushTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.iamkurtgoz.sub.ktlint")
                apply("com.iamkurtgoz.sub.detekt")
                apply("com.iamkurtgoz.sub.spotless")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = rootProject.defaultSecrets().getProperty("TARGET_SDK").toInt()
                compileSdk = rootProject.defaultSecrets().getProperty("COMPILE_SDK").toInt()
            }
            registerPrePushTask()
        }
    }
}
