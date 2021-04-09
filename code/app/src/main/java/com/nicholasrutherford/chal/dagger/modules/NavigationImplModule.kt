package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.peoplelist.PeopleListNavigationImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavigationImplModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleListNavigationImpl(): PeopleListNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeNewsFeedNavigationImpl(): NewsFeedNavigationImpl
}
