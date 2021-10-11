plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.MinSdkVersion
    }

    buildFeatures.viewBinding = true
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Android.AppCompat)
    implementation(Dependencies.Libs.UI.CircleImageView)
    implementation(Dependencies.Libs.UI.ConstraintLayout)
}