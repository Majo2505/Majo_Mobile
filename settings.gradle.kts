rootProject.name = "Majo"

pluginManagement {
    plugins {
        id("dev.detekt") version "2.0.0-alpha.6"
    }
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// Design System incluido en este repo (reemplaza al publishToMavenLocal)
includeBuild("designsystem")

include(":androidApp")
include(":shared")
