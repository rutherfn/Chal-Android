package com.nicholarutherford.chal.more.di

import com.nicholarutherford.chal.more.MoreNavigation
import com.nicholarutherford.chal.more.MoreNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class MoreModule {

    @Binds
    abstract fun bindMoreNavigation(moreNavigationImpl: MoreNavigationImpl): MoreNavigation
}