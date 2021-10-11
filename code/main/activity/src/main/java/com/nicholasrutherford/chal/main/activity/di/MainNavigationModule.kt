package com.nicholasrutherford.chal.main.activity.di

import com.nicholasrutherford.chal.main.activity.MainNavigation
import com.nicholasrutherford.chal.main.activity.MainNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class MainNavigationModule {

    @Binds
    abstract fun bindMainNavigation(mainNavigationImpl: MainNavigationImpl): MainNavigation
}