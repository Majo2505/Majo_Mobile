import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":shared"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.8")
}


android {
    namespace = "ucb.edu.bo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ucb.edu.bo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            buildConfigField("String", "APP_NAME", "\"app-dev\"")
            buildConfigField("String", "BASE_URL", "\"https://dev.api.calyrsoft.com\"")
        }
        create("prod") {
            dimension = "environment"
            applicationIdSuffix = ".app"
            buildConfigField("String", "APP_NAME", "\"app-prod\"")
            buildConfigField("String", "BASE_URL", "\"http://prod.api.calyrsoft.com\"")
        }
    }




    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        resValues = true // Requerido para que funcionen los resValue de los flavors
        buildConfig = true
    }
}