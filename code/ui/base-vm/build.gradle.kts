plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion
}

dependencies {
    api(project(path = ":data:alert"))
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Kotlin.Coroutines)
    implementation(Dependencies.Libs.LifeCycle.ViewModel)
}