plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Kotlin.Coroutines)
    implementation(Dependencies.Libs.LifeCycle.ViewModel)
}