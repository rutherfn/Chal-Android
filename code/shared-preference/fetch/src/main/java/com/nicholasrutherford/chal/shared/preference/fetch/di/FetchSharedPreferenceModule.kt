package com.nicholasrutherford.chal.shared.preference.fetch.di

import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FetchSharedPreferenceModule {

    @Binds
    abstract fun bindFetchSharedPreference(fetchSharedPreferenceImpl: FetchSharedPreferenceImpl): FetchSharedPreference
}