apply(plugin = Dependencies.Plugin.GoogleServices)

plugins {
    id(Dependencies.Plugin.Application)
    kotlin(Dependencies.Plugin.Android)
    kotlin(Dependencies.Plugin.AndroidExtensions)
    kotlin(Dependencies.Plugin.Kapt)
}

android {
    buildToolsVersion(Dependencies.Android.BuildToolsVersion)
    compileSdkVersion(Dependencies.Android.CompileSdkVersion)

    buildFeatures.viewBinding = true


    defaultConfig {
        applicationId = Dependencies.Android.ApplicationId
        minSdkVersion(Dependencies.Android.MinSdkVersion)
        targetSdkVersion(Dependencies.Android.TargetSdkVersion)
        multiDexEnabled =  true
        versionCode = Dependencies.Android.VersionCode
        versionName = Dependencies.Android.VersionName
        testInstrumentationRunner = Dependencies.Android.TestInstrumentationRunner
    }

    buildTypes  {
        getByName(Dependencies.Android.BuildTypes.Release) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    api(project(path = ":account:validation"))
    api(project(path = ":firebase:auth"))
    api(project(path = ":main:resources"))
    api(project(path = ":main:splash"))
    api(project(path = ":network"))
    api(project(path = ":ui:base-vm"))
    api(project(path = ":ui:typefaces"))

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

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    implementation(Dependencies.Libs.Hilt.HiltLifecycleViewModel)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}