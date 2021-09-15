plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
    id(Dependencies.Plugin.DaggerHilt)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.MinSdkVersion
    }

    buildFeatures.viewBinding = true
}

dependencies {
    api(project(path = ":main:navigation"))
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.UI.AcProgress)

    implementation(Dependencies.Libs.Kotlin.Coroutines)

    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}