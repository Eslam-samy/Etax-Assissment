plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.8.10"
}

val apiKey: String = project.findProperty("API_KEY") as String? ?: ""

android {
    namespace = "com.example.etax"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.etax"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true

        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.work.manager)
    implementation(libs.work.manager.dagger)
    ksp(libs.work.manager.dagger.kapt)
    implementation(libs.hilt.compose.navigation)


    implementation(libs.dagger.hilt)
    ksp(libs.dagger.kapt)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.convertor)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation (libs.androidx.room.paging)

    implementation(libs.kotlinx.serialization.json)

    //shared pref
    implementation(libs.androidx.preference.ktx)


    implementation (libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)


}