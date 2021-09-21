package com.nicholasrutherford.chal.firebase.auth.di

import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuthImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ChalFirebaseAuthModule {

    @Binds
    abstract fun bindChalFirebaseAuth(chalFirebaseAuthImpl: ChalFirebaseAuthImpl): ChalFirebaseAuth
}