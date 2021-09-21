package com.nicholasrutherford.chal.account.validation.di

import com.nicholasrutherford.chal.account.validation.AccountValidation
import com.nicholasrutherford.chal.account.validation.AccountValidationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AccountValidationModule {

    @Binds
    abstract fun bindAccountValidation(accountValidationImpl: AccountValidationImpl): AccountValidation
}