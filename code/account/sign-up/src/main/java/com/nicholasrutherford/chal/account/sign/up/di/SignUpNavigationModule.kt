package com.nicholasrutherford.chal.account.sign.up.di

import com.nicholasrutherford.chal.account.sign.up.SignUpNavigation
import com.nicholasrutherford.chal.account.sign.up.SignUpNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class SignUpNavigationModule {

    @Binds
    abstract fun bindSignUpNavigation(signUpNavigationImpl: SignUpNavigationImpl): SignUpNavigation
}