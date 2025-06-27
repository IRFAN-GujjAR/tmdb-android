import java.util.Properties


val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(localPropertiesFile.inputStream())
    }
}

val bearerToken: String = localProperties.getProperty("BEARER_TOKEN") ?: ""


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.irfangujjar.tmdb"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.irfangujjar.tmdb"
        minSdk = 24
        targetSdk = 36
        versionCode = 38
        versionName = "7.6.6"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BEARER_TOKEN", "\"$bearerToken\"")
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
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
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

    implementation(libs.androidx.core.splashscreen)

    // Extended Material Icons
    implementation(libs.androidx.material.icons.extended)


    // Hilt Dependency
    implementation(libs.hilt.android)
    implementation(libs.androidx.constraintlayout)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //Coil for Network Image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //Navigation3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.kotlinx.serialization.core)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.appcheck.playintegrity)
    implementation(libs.firebase.config)
    implementation(libs.firebase.functions)
    

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}