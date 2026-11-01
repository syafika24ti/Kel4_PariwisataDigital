plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.kel4_pariwisatadigital"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.kel4_pariwisatadigital"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Mengaktifkan ViewBinding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("io.coil-kt:coil:2.5.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Tambahan Dependency Modul 9 Praktikum
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    // Library DotsIndicator Modul 9
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}