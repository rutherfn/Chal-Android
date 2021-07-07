plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    buildFeatures.viewBinding = true
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Libs.Android.AppCompat)
    implementation(Dependencies.Libs.Android.Ktx)
    implementation(Dependencies.Libs.Dagger.Android)
    implementation(Dependencies.Libs.Firebase.Auth)
    implementation(Dependencies.Libs.Kotlin.Jdk)
    implementation(Dependencies.Libs.UI.ConstraintLayout)
}