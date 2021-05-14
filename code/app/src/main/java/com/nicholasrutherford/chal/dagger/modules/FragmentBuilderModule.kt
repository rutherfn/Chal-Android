package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleListFragment(): PeopleListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFeedFragment(): NewsFeedFragment
}
