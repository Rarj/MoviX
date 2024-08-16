plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinkapt)
}

android {
    namespace = "com.labs.network.shared"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "AUTH_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4Nzg2N2Q3NTI1ODAyOTllNDJkNTc1ZTlhNGY4YjU0NSIsIm5iZiI6MTcyMzUxNjQ0Ny4wOTIwMTMsInN1YiI6IjYwNTBjNGRjNmRjNmMwMDA2YTBjZjQ0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ZvTojoEh7_osZKiIJNLBYvxU9Vof6sss086_gYL6tyE\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.logging.interceptor)
    api(libs.gson)
    implementation(libs.gson.converter)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    api(libs.paging3)
}

kapt {
    correctErrorTypes = true
}
