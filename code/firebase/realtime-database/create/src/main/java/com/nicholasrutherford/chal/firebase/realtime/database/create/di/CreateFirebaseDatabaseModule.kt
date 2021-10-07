package com.nicholasrutherford.chal.firebase.realtime.database.create.di

import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CreateFirebaseDatabaseModule {

    @Binds
    abstract fun bindCreateFirebaseDatabase(createFirebaseDatabaseImpl: CreateFirebaseDatabaseImpl): CreateFirebaseDatabase
}