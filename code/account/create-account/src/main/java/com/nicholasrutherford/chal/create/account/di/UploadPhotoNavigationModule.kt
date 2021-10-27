package com.nicholasrutherford.chal.create.account.di

import com.nicholasrutherford.chal.create.account.uploadphoto.UploadPhotoNavigation
import com.nicholasrutherford.chal.create.account.uploadphoto.UploadPhotoNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UploadPhotoNavigationModule {

    @Binds
    abstract fun bindUploadPhotoNavigationImpl(uploadPhotoNavigationImpl: UploadPhotoNavigationImpl): UploadPhotoNavigation
}