
object Dependencies {
    object Plugin {
        const val android = "android"
        const val application = "com.android.application"
        const val androidExtensions = "android.extensions"
        const val kapt = "kapt"
    }
    object Android {
        const val applicationId = "com.nicholasrutherford.chal"
        const val buildToolsVersion = "29.0.3"
        const val compileSdkVersion = 30
        const val minSdkVersion = 16
        const val targetSdkVersion = 30
        const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        const val versionCode = 1
        const val versionName = "1.0"

        object BuildTypes  {
            const val debug = "debug"
            const val staging = "staging"
            const val release = "release"
        }
    }
    object Version {
        const val acProgressVersion = "1.2.1"
        const val appCompatVersion = "1.1.0"
        const val circleImageViewVersion = "3.1.0"
        const val constraintLayoutVersion = "1.1.3"
        const val daggerVersion = "2.27"
        const val espressoCoreVersion = "3.2.0"
        const val firebaseAnalyticsVersion = "17.4.4"
        const val firebaseAuthVersion = "19.3.2"
        const val firebaseDatabaseVersion = "19.3.1"
        const val firebaseStorageVersion = "19.1.1"
        const val glideVersion = "4.11.0"
        const val glideCompilerVersion = "4.11.0"
        const val gsonVersion = "2.8.6"
        const val junitVersion = "1.1.1"
        const val junitTestImplementationVersion = "4.13"
        const val kotlinCoroutinesVersion = "1.4.0"
        const val kotlinVersion = "1.4.20"
        const val ktxVersion = "1.3.1"
        const val lifecycleVersion = "2.2.0"
        const val materialVersion = "1.1.0"
        const val picassoVersion = "2.71828"
        const val roomVersion = "2.2.5"
        const val swipeRefreshVersion = "1.1.0"
        const val testFairyVersion = "1.11.44"
        const val timberVersion = "4.7.1"
    }
    object Libs {

        object Android {
            const val AppCompat = "androidx.appcompat:appcompat:${Version.appCompatVersion}"
            const val Ktx = "androidx.core:core-ktx:${Version.ktxVersion}"
        }

        object Debug {
            const val TestFairy = "testfairy:testfairy-android-sdk:${Version.testFairyVersion}"
            const val Timber = "com.jakewharton.timber:timber:${Version.timberVersion}"
        }

        object Dagger {
            const val Android = "com.google.dagger:dagger-android:${Version.daggerVersion}"
            const val Compiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
            const val Native = "com.google.dagger:dagger:${Version.daggerVersion}"
            const val Processor = "com.google.dagger:dagger-android-processor:${Version.daggerVersion}"
            const val Support = "com.google.dagger:dagger-android-support:${Version.daggerVersion}"
        }

        object Firebase {
            const val Auth = "com.google.firebase:firebase-auth:${Version.firebaseAuthVersion}"
            const val Analytics = "com.google.firebase:firebase-analytics:${Version.firebaseAnalyticsVersion}"
            const val Database = "com.google.firebase:firebase-database:${Version.firebaseDatabaseVersion}"
            const val Storage = "com.google.firebase:firebase-storage:${Version.firebaseStorageVersion}"
        }

        object Gson {
            const val Native = "com.google.code.gson:gson:${Version.gsonVersion}"
        }

        object Image {
            const val Glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
            const val GlideCompiler = "com.github.bumptech.glide:compiler:${Version.glideCompilerVersion}"
            const val Picasso = "com.squareup.picasso:picasso:${Version.picassoVersion}"
        }

        object LifeCycle {
            const val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycleVersion}"
            const val RunTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleVersion}"
            const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"
        }

        object Kotlin {
            const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutinesVersion}"
            const val Jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7${Version.kotlinVersion}"
        }

        object Room {
            const val Compiler = "androidx.room:room-compiler:${Version.roomVersion}"
            const val Ktx = "androidx.room:room-ktx:${Version.roomVersion}"
            const val Runtime = "androidx.room:room-runtime:${Version.roomVersion}"
        }

        object UI {
            const val AcProgress = "cc.cloudist.acplibrary:library:${Version.acProgressVersion}"
            const val CircleImageView = "de.hdodenhof:circleimageview:${Version.circleImageViewVersion}"
            const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
            const val Material = "com.google.android.material:material:${Version.materialVersion}"
            const val SwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshVersion}"
        }
    }
    object LibsTest {

        object Espresso {
            const val Core = "androidx.test.espresso:espresso-core:${Version.espressoCoreVersion}"
        }

        object Junit {
            const val Native = "junit:junit:${Version.junitVersion}"
            const val JunitTestImplementation = "androidx.test.ext:junit:${Version.junitTestImplementationVersion}"
        }
    }

}