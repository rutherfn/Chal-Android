plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)

    defaultConfig {
        minSdkVersion(Dependencies.Android.MinSdkVersion)
    }
}

dependencies {
    implementation(Dependencies.Libs.Dagger.Android)

    implementation(Dependencies.Libs.Firebase.Auth)
    implementation(Dependencies.Libs.Kotlin.Coroutines)
}