plugins {
    id (Dependencies.Plugin.Library)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion
}
dependencies {
    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)
}
