package com.nicholasrutherford.chal.profile.di

import com.nicholasrutherford.chal.profile.ProfileNavigation
import com.nicholasrutherford.chal.profile.ProfileNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ProfileNavigationModule {

    @Binds
    abstract fun bindProfileNav(profileNavigationImpl: ProfileNavigationImpl): ProfileNavigation
}