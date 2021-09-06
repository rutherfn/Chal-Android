plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.Kapt)
    id(Dependencies.Plugin.DaggerHilt)
}

android {
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)

    defaultConfig {
        minSdkVersion(Dependencies.Android.MinSdkVersion)
    }
}

dependencies {
    implementation(Dependencies.Libs.Dagger.Android)

    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}