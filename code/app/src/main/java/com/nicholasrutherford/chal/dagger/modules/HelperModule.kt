package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.main.helper.MainActivityHelperImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HelperModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityHelper(): MainActivityHelperImpl
}