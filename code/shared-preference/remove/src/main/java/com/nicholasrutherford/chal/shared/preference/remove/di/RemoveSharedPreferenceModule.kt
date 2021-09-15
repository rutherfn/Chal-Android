package com.nicholasrutherford.chal.shared.preference.remove.di

import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoveSharedPreferenceModule {

    @Binds
    abstract fun bindRemoveSharedPreference(removeSharedPreferenceImpl: RemoveSharedPreferenceImpl): RemoveSharedPreference
}