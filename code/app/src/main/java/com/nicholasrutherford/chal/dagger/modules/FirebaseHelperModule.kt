package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.firebasehelper.ChalFirebaseAuthImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FirebaseHelperModule {

    @ContributesAndroidInjector
    abstract fun contributeChalFirebaseAuth(): ChalFirebaseAuthImpl
}