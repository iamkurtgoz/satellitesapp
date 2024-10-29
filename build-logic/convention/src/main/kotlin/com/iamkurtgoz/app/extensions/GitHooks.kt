package com.iamkurtgoz.app.extensions

import com.iamkurtgoz.app.utils.GitHooksTask
import org.gradle.api.Project

fun Project.registerPrePushTask() {
    tasks.register("installGitHooks", GitHooksTask::class.java) {
        group = "git-hooks"
        val projectDirectory = rootProject.layout.projectDirectory
        hookSource.set(projectDirectory.file("scripts/pre-commit.sh"))
        hookOutput.set(projectDirectory.file(".git/hooks/pre-commit"))
    }
}
