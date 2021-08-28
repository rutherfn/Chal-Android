package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.main.helper.MainActivityHelperImpl
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HelperModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityHelper(): MainActivityHelperImpl

    @ContributesAndroidInjector
    abstract fun contributeTypefaces(): TypefacesImpl
}