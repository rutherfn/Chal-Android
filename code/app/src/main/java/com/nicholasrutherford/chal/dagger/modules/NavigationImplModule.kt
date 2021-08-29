package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.navigationimpl.addedprogress.AddedProgressNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.editmyprofile.EditProfileNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.login.RedesignLoginNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.main.MainNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.more.MoreNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.peoplelist.PeopleListNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.progressupload.ProgressUploadNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.splash.SplashNavigationImpl
import com.nicholasrutherford.chal.navigationimpl.splash.SplashRedesignNavigationImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavigationImplModule {

    @ContributesAndroidInjector
    abstract fun contributesAddedProgressNavigationImpl(): AddedProgressNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeEditProfileNavigationImpl(): EditProfileNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeMainNavigationImpl(): MainNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeMoreNavigationImpl(): MoreNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeNewsFeedNavigationImpl(): NewsFeedNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributePeopleListNavigationImpl(): PeopleListNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeProgressUploadNavigationImpl(): ProgressUploadNavigationImpl

    @ContributesAndroidInjector
    abstract fun contributeSplashNavigationImpl(): SplashRedesignNavigationImpl
}
