package com.nicholasrutherford.chal.challenge.detail.di

import com.nicholasrutherford.chal.challenge.detail.ChallengeDetailNavigation
import com.nicholasrutherford.chal.challenge.detail.ChallengeDetailNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ChallengeDetailNavigationModule {

    @Binds
    abstract fun bindChallengeDetailNavigation(challengeDetailNavigationImpl: ChallengeDetailNavigationImpl) : ChallengeDetailNavigation
}