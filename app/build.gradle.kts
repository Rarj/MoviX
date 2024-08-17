plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinkapt)
}

android {
    namespace = "com.labs.movix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.labs.movix"
        minSdk = 23
        targetSdk = 34
        versionCode = 200001
        versionName = "2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    implementation(project(":home:ui"))
    implementation(project(":navigation:home:controller"))

    implementation(project(":search:ui"))
    implementation(project(":navigation:search:controller"))
    implementation(project(":navigation:search:impl"))

    implementation(project(":detail:ui"))
    implementation(project(":navigation:detail:controller"))
    implementation(project(":navigation:detail:impl"))

    implementation(libs.appcompat)
    implementation(libs.compose.material3)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)

    implementation(libs.coil)
    implementation(libs.paging.compose)
}

kapt {
    correctErrorTypes = true
}
