package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.newsfeed.ChallengesHeaderAdapter
import com.nicholasrutherford.chal.peoplelist.PeopleListAdapter
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AdapterModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleListAdapter(): PeopleListAdapter

    @ContributesAndroidInjector
    abstract fun contributeChallengesHeaderAdapter(): ChallengesHeaderAdapter
}
