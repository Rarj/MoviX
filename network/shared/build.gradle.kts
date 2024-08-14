plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
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
        buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w342/\"")
        buildConfigField("String", "BACKDROP_IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w780/\"")
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
    implementation(libs.gson)
    implementation(libs.paging3)
}