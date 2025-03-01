plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinkapt)
    alias(libs.plugins.googleService)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.arj.movix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arj.movix"
        minSdk = 23
        targetSdk = 34
        versionCode = 200305
        versionName = "2.3.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false

            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            resValue(type = "string", name = "app_name", value = "MoviX Debug")
        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
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

    implementation(project(":common"))

    implementation(libs.appcompat)
    implementation(libs.compose.material3)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)

    implementation(libs.coil)
    implementation(libs.paging.compose)

    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
}

kapt {
    correctErrorTypes = true
}
