package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.helpers.testfairy.ChalTestFairyImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestFairyModule {

    @ContributesAndroidInjector
    abstract fun contributeChalTestFairyImpl(): ChalTestFairyImpl
}