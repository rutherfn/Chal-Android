plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
    id(Dependencies.Plugin.DaggerHilt)
}

android {
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)

    buildFeatures.viewBinding = true
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}