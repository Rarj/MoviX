@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.labs.movix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.labs.movix"
        minSdk = 23
        targetSdk = 34
        versionCode = 200000
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":uikit"))
    implementation(project(":home:ui"))
    implementation(project(":home:api"))
    implementation(project(":home:impl"))
    implementation(project(":navigation:home:controller"))
    implementation(project(":search:ui"))
    implementation(project(":navigation:search:controller"))
    implementation(project(":navigation:search:impl"))

    implementation(project(":detail:ui"))
    implementation(project(":navigation:detail:controller"))
    implementation(project(":navigation:detail:impl"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.ktx)
    implementation(libs.navigation.ui)
    implementation(libs.viewmodelscope)

    kapt(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt)

    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)

    implementation(libs.coil)
    implementation(libs.paging.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

kapt {
    correctErrorTypes = true
}
