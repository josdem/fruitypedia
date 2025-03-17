// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.diffplug.spotless") version "6.22.0"
}

val testIntegrationVersion = "1.6.1"
val fragmentVersion = "1.8.1"

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
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.9")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.9")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
    implementation("com.google.firebase:firebase-analytics")

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    // TODO: Remove debugImplementation since it is a temporary fix for this JUnit issue:
    // https://github.com/android/android-test/issues/1755
    //noinspection GradleDependency
    debugImplementation("androidx.fragment:fragment-testing-manifest:$fragmentVersion")
    //noinspection GradleDependency
    androidTestImplementation("androidx.fragment:fragment-testing:$fragmentVersion")
    debugImplementation("androidx.tracing:tracing:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    //noinspection GradleDependency
    androidTestImplementation("androidx.test:runner:$testIntegrationVersion")
    androidTestImplementation("androidx.test:rules:$testIntegrationVersion")
}
