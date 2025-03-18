// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.diffplug.spotless)
}

android {
    namespace = "com.josdem.fruitypedia"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.josdem.fruitypedia"
        minSdk = 21
        targetSdk = 35
        versionCode = 8
        versionName = "1.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)

    // TODO: Remove debugImplementation since it is a temporary fix for this JUnit issue:
    // https://github.com/android/android-test/issues/1755
    //noinspection GradleDependency
    debugImplementation(libs.androidx.fragment.testing.manifest)
    //noinspection GradleDependency
    androidTestImplementation(libs.androidx.fragment.testing)
    debugImplementation(libs.androidx.tracing)
    androidTestImplementation(libs.androidx.espresso.core)
    //noinspection GradleDependency
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)
}