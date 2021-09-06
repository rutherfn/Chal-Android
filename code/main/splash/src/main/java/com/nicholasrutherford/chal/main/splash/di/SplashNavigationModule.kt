package com.nicholasrutherford.chal.main.splash.di

import com.nicholasrutherford.chal.main.splash.SplashNavigation
import com.nicholasrutherford.chal.main.splash.SplashNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class SplashNavigationModule {

    @Binds
    abstract fun bindSplashNavigation(splashNavigationImpl: SplashNavigationImpl): SplashNavigation
}