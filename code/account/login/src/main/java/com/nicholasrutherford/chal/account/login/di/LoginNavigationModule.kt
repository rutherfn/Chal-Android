package com.nicholasrutherford.chal.account.login.di

import com.nicholasrutherford.chal.account.login.LoginNavigation
import com.nicholasrutherford.chal.account.login.LoginNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class LoginNavigationModule {

    @Binds
    abstract fun bindLoginNavigation(loginNavigationImpl: LoginNavigationImpl): LoginNavigation
}