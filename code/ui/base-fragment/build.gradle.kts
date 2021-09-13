plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
    id(Dependencies.Plugin.DaggerHilt)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion

    buildFeatures.viewBinding = true
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.UI.AcProgress)

    implementation(Dependencies.Libs.Kotlin.Coroutines)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    implementation(project(mapOf("path" to ":main:navigation")))
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}