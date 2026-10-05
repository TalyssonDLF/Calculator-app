plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.lojavisual"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lojavisual"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
