package com.nicholasrutherford.chal.main.upload.progress.di

import com.nicholasrutherford.chal.main.upload.progress.addedprogress.AddedProgressNavigation
import com.nicholasrutherford.chal.main.upload.progress.addedprogress.AddedProgressNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AddedProgressNavigationModule {

    @Binds
    abstract fun bindAddedProgressNavigation(addedProgressNavigationImpl: AddedProgressNavigationImpl) : AddedProgressNavigation
}