plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.kotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.kotlinkapt)
}

android {
	namespace = "com.cinepeek.login.di"
	compileSdk = 34

	defaultConfig {
		minSdk = 23

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
	implementation(project(":network:shared"))
	implementation(project(":network:state"))

	implementation(project(":login:impl"))
	implementation(project(":login:api"))
	implementation(project(":login:domain"))

	implementation(libs.dagger.hilt)
	kapt(libs.dagger.hilt.compiler)
}

kapt {
	correctErrorTypes = true
}
