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
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Dagger.Android)
}