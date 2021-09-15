plugins {
    id (Dependencies.Plugin.Library)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
    id(Dependencies.Plugin.DaggerHilt)
}

android {
    compileSdk = Dependencies.Android.CompileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.MinSdkVersion
    }

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions.exclude("META-INF/main.kotlin_module")
}

dependencies {
    api(project(path = ":account:create-account"))
    api(project(path = ":account:forgot-password"))
    api(project(path = ":account:login"))
    api(project(path = ":account:sign-up"))
    api(project(path = ":firebase:auth"))
    api(project(path = ":main:navigation"))
    api(project(path = ":main:resources"))
    api(project(path = ":main:splash"))
    api(project(path = ":shared-preference:create"))
    api(project(path = ":ui:base-fragment"))
    api(project(path = ":ui:base-vm"))

    implementation(Dependencies.Libs.Android.AppCompat)
    implementation(Dependencies.Libs.Android.Ktx)
    implementation(Dependencies.Libs.UI.ConstraintLayout)

    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)

    implementation(Dependencies.Libs.Dagger.Android)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    implementation(Dependencies.Libs.Hilt.HiltLifecycleViewModel)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}