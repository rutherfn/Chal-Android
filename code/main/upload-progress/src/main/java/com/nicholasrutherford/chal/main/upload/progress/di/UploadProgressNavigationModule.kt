package com.nicholasrutherford.chal.main.upload.progress.di

import com.nicholasrutherford.chal.main.upload.progress.UploadProgressNavigation
import com.nicholasrutherford.chal.main.upload.progress.UploadProgressNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UploadProgressNavigationModule {

    @Binds
    abstract fun bindUploadMoreNavigation(uploadProgressNavigationImpl: UploadProgressNavigationImpl): UploadProgressNavigation
}