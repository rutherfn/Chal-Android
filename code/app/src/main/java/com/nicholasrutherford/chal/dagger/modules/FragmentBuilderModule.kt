package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.more.MoreFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.profile.editprofile.EditProfileFragment
import com.nicholasrutherford.chal.profile.profiles.MyProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeEditProfileFragment(): EditProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeMoreFragment(): MoreFragment

    @ContributesAndroidInjector
    abstract fun contributeMyProfileFragment(): MyProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFeedFragment(): NewsFeedFragment

    @ContributesAndroidInjector
    abstract fun contributePeopleListFragment(): PeopleListFragment
}
