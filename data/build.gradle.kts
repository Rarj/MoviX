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

        buildConfigField("String", "AUTH_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4Nzg2N2Q3NTI1ODAyOTllNDJkNTc1ZTlhNGY4YjU0NSIsIm5iZiI6MTcyMzUxNjQ0Ny4wOTIwMTMsInN1YiI6IjYwNTBjNGRjNmRjNmMwMDA2YTBjZjQ0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ZvTojoEh7_osZKiIJNLBYvxU9Vof6sss086_gYL6tyE\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w342/\"")
        buildConfigField("String", "BACKDROP_IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w780/\"")
    }

    buildTypes {
        release {
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":home:api"))
    implementation(project(":search:api"))
    implementation(project(":detail:api"))
    implementation(project(":review:api"))

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
