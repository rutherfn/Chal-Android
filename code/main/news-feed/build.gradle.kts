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
    api(project(path = ":challenge:list"))
    api(project(path = ":helper:fragment"))
    api(project(path = ":firebase:realtime-database:create"))
    api(project(path = ":firebase:realtime-database:fetch"))
    api(project(path = ":main:resources"))
    api(project(path = ":more"))
    api(project(path = ":shared-preference:fetch"))
    api(project(path = ":shared-preference:remove"))
    api(project(path = ":ui:base-fragment"))
    api(project(path = ":ui:base-vm"))
    api(project(path = ":ui:shared-layout"))
    api(project(path = ":ui:typefaces"))

    implementation(Dependencies.Libs.Android.AppCompat)
    implementation(Dependencies.Libs.Android.Ktx)
    implementation(Dependencies.Libs.UI.ConstraintLayout)
    implementation(Dependencies.Libs.UI.CircleImageView)

    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)

    implementation(Dependencies.Libs.Image.Glide)
    annotationProcessor(Dependencies.Libs.Image.GlideCompiler)
    implementation(Dependencies.Libs.Image.Picasso)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    implementation(Dependencies.Libs.Hilt.HiltLifecycleViewModel)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}