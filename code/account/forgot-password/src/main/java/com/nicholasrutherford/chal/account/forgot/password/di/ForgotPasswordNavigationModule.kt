package com.nicholasrutherford.chal.account.forgot.password.di

import com.nicholasrutherford.chal.account.forgot.password.ForgotPasswordNavigation
import com.nicholasrutherford.chal.account.forgot.password.ForgotPasswordNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ForgotPasswordNavigationModule {

    @Binds
    abstract fun bindForgotPasswordNavigation(forgotPasswordNavigationImpl: ForgotPasswordNavigationImpl): ForgotPasswordNavigation
}