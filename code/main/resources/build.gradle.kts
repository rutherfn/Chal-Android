plugins {
    id (Dependencies.Plugin.Library)
}

android {
    defaultConfig {
        minSdk = Dependencies.Android.MinSdkVersion
    }

    compileSdk = Dependencies.Android.CompileSdkVersion
}
dependencies {
    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)
}
