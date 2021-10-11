package com.nicholasrutherford.chal.main.news.feed.di

import com.nicholasrutherford.chal.main.news.feed.NewsFeedNavigation
import com.nicholasrutherford.chal.main.news.feed.NewsFeedNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class NewsFeedNavigationModule {

    @Binds
    abstract fun bindNewsFeedNavigation(newsFeedNavigationImpl: NewsFeedNavigationImpl): NewsFeedNavigation
}