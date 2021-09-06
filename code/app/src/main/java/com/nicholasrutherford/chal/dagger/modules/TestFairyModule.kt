package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.helpers.testfairy.ChalTestFairyImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class TestFairyModule {

    @ContributesAndroidInjector
    abstract fun contributeChalTestFairyImpl(): ChalTestFairyImpl
}