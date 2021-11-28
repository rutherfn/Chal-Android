plugins {
    id(Dependencies.Plugin.Application)
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

    defaultConfig {
        applicationId = Dependencies.Android.ApplicationId
        targetSdk = Dependencies.Android.TargetSdkVersion
        minSdk = Dependencies.Android.MinSdkVersion
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
    api(project(path = ":main:navigation"))
    api(project(path = ":main:resources"))
    api(project(path = ":firebase:realtime-database:create"))
    api(project(path = ":ui:base-fragment"))
    api(project(path = ":ui:base-vm"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Libs.Android.AppCompat)
    implementation(Dependencies.Libs.Android.Ktx)
    implementation(Dependencies.Libs.UI.ConstraintLayout)

    implementation(Dependencies.Libs.Debug.Timber)

    implementation(Dependencies.Libs.Navigation.NavigationFragment)
    implementation(Dependencies.Libs.Navigation.NavigationUi)

    implementation(Dependencies.Libs.Hilt.DaggerHiltAndroid)
    kapt(Dependencies.Libs.Hilt.DaggerHiltAndroidCompiler)
    implementation(Dependencies.Libs.Hilt.HiltLifecycleViewModel)
    kapt(Dependencies.Libs.Hilt.hiltCompiler)
}