plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}


android {
    namespace = "com.anirudh.spotifysearch"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.anirudh.spotifysearch"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.fragment:fragment:1.5.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutine

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("com.github.bumptech.glide:glide:4.11.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")

    kapt("androidx.room:room-compiler:2.5.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

    implementation("androidx.room:room-ktx:2.5.2")


    /* Dagger2 - We are going to use dagger.android which includes
     * support for Activity and fragment injection so we need to include
     * the following dependencies */
    implementation("com.google.dagger:dagger-android:2.17")
    implementation("com.google.dagger:dagger-android-support:2.17")
    kapt("com.google.dagger:dagger-android-processor:2.17")

    /* Dagger2 - default dependency */
    kapt("com.google.dagger:dagger-compiler:2.17")


    /* Dagger2 - default dependency */
}