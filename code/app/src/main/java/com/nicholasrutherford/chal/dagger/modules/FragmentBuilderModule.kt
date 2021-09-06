package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.account.forgotpasswordredesign.ForgotPasswordRedesignFragment
import com.nicholasrutherford.chal.account.redesignlogin.RedesignLoginFragment
import com.nicholasrutherford.chal.account.redesignsignup.RedesignSignUpFragment
import com.nicholasrutherford.chal.addedProgress.AddedProgressFragment
import com.nicholasrutherford.chal.more.MoreFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.profile.editprofile.EditProfileFragment
import com.nicholasrutherford.chal.profile.profiles.MyProfileFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadFragment
import com.nicholasrutherford.chal.splashredesign.SplashRedesignFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAddedProgressFragment(): AddedProgressFragment

    @ContributesAndroidInjector
    abstract fun contributeEditProfileFragment(): EditProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordRedesignFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): RedesignLoginFragment

    @ContributesAndroidInjector
    abstract fun contributeMoreFragment(): MoreFragment

    @ContributesAndroidInjector
    abstract fun contributeMyProfileFragment(): MyProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFeedFragment(): NewsFeedFragment

    @ContributesAndroidInjector
    abstract fun contributePeopleListFragment(): PeopleListFragment

    @ContributesAndroidInjector
    abstract fun contributeProgressUploadFragment(): ProgressUploadFragment

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragment(): RedesignSignUpFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashRedesignFragment
}
