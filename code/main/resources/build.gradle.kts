plugins {
    id (Dependencies.Plugin.Library)
}

android {
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)
}
dependencies {
    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)
}
