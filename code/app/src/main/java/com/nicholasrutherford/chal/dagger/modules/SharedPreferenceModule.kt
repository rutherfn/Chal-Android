package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.helpers.sharedpreference.ReadSharedPreferenceJson
import com.nicholasrutherford.chal.helpers.sharedpreference.clearsharedpreference.ClearSharedPreferenceImpl
import com.nicholasrutherford.chal.helpers.sharedpreference.readsharedpreference.ReadSharedPreferenceImpl
import com.nicholasrutherford.chal.helpers.sharedpreference.updatesharedpreference.UpdateSharedPreferenceImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class SharedPreferenceModule {

    @ContributesAndroidInjector
    abstract fun contributeClearSharedPreferenceImpl(): ClearSharedPreferenceImpl

    @ContributesAndroidInjector
    abstract fun contributeReadSharedPreferenceImpl(): ReadSharedPreferenceImpl

    @ContributesAndroidInjector
    abstract fun contributeReadSharedPreferenceJson(): ReadSharedPreferenceJson

    @ContributesAndroidInjector
    abstract fun contributeUpdateSharedPreferenceImpl(): UpdateSharedPreferenceImpl
}