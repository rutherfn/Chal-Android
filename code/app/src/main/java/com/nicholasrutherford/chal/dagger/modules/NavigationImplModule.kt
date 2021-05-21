package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.navigationimpl.editmyprofile.EditProfileNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.more.MoreNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.peoplelist.PeopleListNavigationImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavigationImplModule {

    @ContributesAndroidInjector
    abstract fun contributeEditProfileNavigationImpl(): EditProfileNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeMoreNavigationImpl(): MoreNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeNewsFeedNavigationImpl(): NewsFeedNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributePeopleListNavigationImpl(): PeopleListNavigationImpl
}
