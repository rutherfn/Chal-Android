package com.nicholasrutherford.chal.shared.preference.create.di

import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CreateSharedPreferenceModule {

    @Binds
    abstract fun bindACreateSharedPreference(createSharedPreferenceImpl: CreateSharedPreferenceImpl): CreateSharedPreference
}