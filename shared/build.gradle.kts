import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import dev.detekt.gradle.Detekt

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room3)
    alias(libs.plugins.wire)
}
detekt {
    source.setFrom(
        "src/commonMain/kotlin",
        "src/androidMain/kotlin",
        "src/iosMain/kotlin"
    )
    config.setFrom(files("${rootDir}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    ignoreFailures = false
}

tasks.withType<Detekt>().configureEach {
    include("**/*.kt", "**/*.kts")
    exclude("**/build/**")
}

tasks.register<Detekt>("detektAll") {
    description = "Analiza todo el código fuente KMP"
    group = "verification"
    setSource(files("src"))
    include("**/*.kt", "**/*.kts")
    exclude("**/build/**")

    reports {
        html.required.set(true)
        sarif.required.set(true)
    }
}


kotlin {
    
    android {
       namespace = "ucb.edu.bo.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_17
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
       withDeviceTestBuilder {
           sourceSetTreeName = "test"
       }.configure {
           instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.androidx.room3.sqlite.wrapper)
            implementation(libs.koin.android)
            implementation(libs.androidx.work.runtime.ktx)
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:34.0.0"))
            implementation("com.google.firebase:firebase-messaging")
            implementation("com.google.firebase:firebase-analytics")
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.navigation.compose)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.kotlinx.serialization.json)
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation(libs.ktor.client.okhttp)
            implementation(libs.androidx.room3.runtime)
            implementation(libs.androidx.sqlite.bundled)
            implementation(libs.firebase.database)
            implementation(libs.firebase.config)
            implementation(libs.firebase.firestore)
            implementation(libs.wire.runtime)
            implementation(libs.wire.grpc.client)
            implementation("edu.ucb.ds:shared:1.0.0-SNAPSHOT")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
    add("kspAndroid", libs.androidx.room3.compiler)

}
room3 {
    schemaDirectory("$projectDir/schemas")
}

wire {
    sourcePath {
        srcDir("src/commonMain/kotlin/proto")
    }
    kotlin {
        rpcRole = "client"
    }
}
