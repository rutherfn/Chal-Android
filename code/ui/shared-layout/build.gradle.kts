plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)

    defaultConfig {
        minSdkVersion(Dependencies.Android.MinSdkVersion)
    }

    buildFeatures.viewBinding = true
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Android.AppCompat)
    implementation(Dependencies.Libs.UI.ConstraintLayout)
}