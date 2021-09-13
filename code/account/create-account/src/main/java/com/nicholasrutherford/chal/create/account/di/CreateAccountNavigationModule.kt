package com.nicholasrutherford.chal.create.account.di

import com.nicholasrutherford.chal.create.account.CreateAccountNavigation
import com.nicholasrutherford.chal.create.account.CreateAccountNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CreateAccountNavigationModule {

    @Binds
    abstract fun bindCreateAccountNavigation(createAccountNavigationImpl: CreateAccountNavigationImpl): CreateAccountNavigation
}