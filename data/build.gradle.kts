@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.labs.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "AUTH_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNDUzZjEwZDY0NzkyZjY1ODlkYWZhMmM5OGQ1N2RlMCIsInN1YiI6IjYwNTBjNGRjNmRjNmMwMDA2YTBjZjQ0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.crN3sS7S8naVeJ6TbJMG7FXlt8JOc3SuZrCMi7O1Kk0\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w342/\"")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.gson)
    implementation(libs.gson.converter)

    kapt(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt)

    implementation(libs.logging.interceptor)

    api(libs.paging3)

}

kapt {
    correctErrorTypes = true
}