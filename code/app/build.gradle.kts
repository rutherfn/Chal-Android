// apply plugin: "com.google.gms.google-services" // need to come back to this and find out how we are going to import this

plugins {
    id(Dependencies.Plugin.application)
    kotlin(Dependencies.Plugin.android)
    kotlin(Dependencies.Plugin.androidExtensions)
    kotlin(Dependencies.Plugin.kapt)
}

android {
    buildToolsVersion(Dependencies.Android.buildToolsVersion)
    compileSdkVersion(Dependencies.Android.compileSdkVersion)

    buildFeatures.viewBinding = true


    defaultConfig {
        applicationId = Dependencies.Android.applicationId
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName
        testInstrumentationRunner = Dependencies.Android.testInstrumentationRunner
    }

    buildTypes  {
        getByName(Dependencies.Android.BuildTypes.release) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    api(project(path = ":main:resources"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Libs.UI.AcProgress)
    implementation(Dependencies.Libs.Android.AppCompat)

    implementation(Dependencies.Libs.Debug.TestFairy)
    implementation(Dependencies.Libs.Debug.Timber)
    implementation(Dependencies.Libs.Kotlin.Coroutines)
    implementation(Dependencies.Libs.UI.SwipeRefreshLayout)

    implementation(Dependencies.Libs.UI.ConstraintLayout)
    implementation(Dependencies.Libs.UI.CircleImageView)

    implementation(Dependencies.Libs.Kotlin.Jdk)
    implementation(Dependencies.Libs.Android.Ktx)
    implementation(Dependencies.Libs.UI.Material)

    implementation(Dependencies.Libs.LifeCycle.LiveData)
    implementation(Dependencies.Libs.LifeCycle.RunTime)
    implementation(Dependencies.Libs.LifeCycle.ViewModel)

    implementation(Dependencies.Libs.Image.Glide)
    annotationProcessor(Dependencies.Libs.Image.GlideCompiler)
    implementation(Dependencies.Libs.Image.Picasso)

    implementation(Dependencies.Libs.Firebase.Auth)
    implementation(Dependencies.Libs.Firebase.Analytics)
    implementation(Dependencies.Libs.Firebase.Database)
    implementation(Dependencies.Libs.Firebase.Storage)

    androidTestImplementation(Dependencies.LibsTest.Espresso.Core)
    androidTestImplementation(Dependencies.LibsTest.Junit.JunitTestImplementation)
    testImplementation(Dependencies.LibsTest.Junit.Native)

    implementation(Dependencies.Libs.Gson.Native)

    kapt(Dependencies.Libs.Room.Compiler)
    implementation(Dependencies.Libs.Room.Ktx)
    implementation(Dependencies.Libs.Room.Runtime)

    implementation(Dependencies.Libs.Dagger.Android)
    implementation(Dependencies.Libs.Dagger.Native)
    implementation(Dependencies.Libs.Dagger.Support)

    kapt(Dependencies.Libs.Dagger.Compiler)
    kapt(Dependencies.Libs.Dagger.Processor)
}

// dependencies {
//     implementation 'com.android.support:multidex:1.0.3' // keep just in case
//     // color picker keep this here just in case
//     implementation 'com.github.yukuku:ambilwarna:2.0.1' '
//
//     //Login Buttons keep this here just in case
//     implementation 'com.shaishavgandhi:login-buttons:1.0.0'