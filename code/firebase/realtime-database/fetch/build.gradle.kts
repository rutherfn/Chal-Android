apply(plugin = Dependencies.Plugin.GoogleServices)

plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.Kapt)
    id(Dependencies.Plugin.DaggerHilt)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.MinSdkVersion
    }
}

dependencies {
    api(project(path = ":data:challenges"))
    api(project(path = ":data:post"))
    api(project(path = ":firebase:auth"))
    api(project(path = ":helper:constants"))
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Dagger.Android)

    implementation(Dependencies.Libs.Firebase.Database)
    implementation(Dependencies.Libs.Kotlin.Coroutines)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}