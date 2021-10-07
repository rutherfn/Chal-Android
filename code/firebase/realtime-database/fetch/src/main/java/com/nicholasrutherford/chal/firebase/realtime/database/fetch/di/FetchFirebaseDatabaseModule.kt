package com.nicholasrutherford.chal.firebase.realtime.database.fetch.di

import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FetchFirebaseDatabaseModule {

    @Binds
    abstract fun bindFetchFirebaseDatabase(fetchFirebaseDatabaseImpl: FetchFirebaseDatabaseImpl): FetchFirebaseDatabase
}