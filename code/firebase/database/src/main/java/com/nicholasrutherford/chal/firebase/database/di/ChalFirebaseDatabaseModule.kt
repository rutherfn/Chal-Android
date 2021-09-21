package com.nicholasrutherford.chal.firebase.database.di

import com.nicholasrutherford.chal.firebase.database.ChalFirebaseDatabase
import com.nicholasrutherford.chal.firebase.database.ChalFirebaseDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ChalFirebaseDatabaseModule {

    @Binds
    abstract fun bindChalFirebaseDatabase(chalFirebaseDatabseImpl: ChalFirebaseDatabaseImpl): ChalFirebaseDatabase
}