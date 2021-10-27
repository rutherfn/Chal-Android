package com.nicholasrutherford.chal.profile.di

import com.nicholasrutherford.chal.profile.edit.EditProfileNavigation
import com.nicholasrutherford.chal.profile.edit.EditProfileNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class EditProfileNavigationModule {

    @Binds
    abstract fun bindEditProfileNav(editProfileNavigationImpl: EditProfileNavigationImpl): EditProfileNavigation
}