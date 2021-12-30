package com.nicholasrutherford.chal.firebase.realtime.database.delete.di

import com.nicholasrutherford.chal.firebase.realtime.database.delete.DeleteFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.delete.DeleteFirebaseDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DeleteFirebaseDatabaseModule {

    @Binds
    abstract fun bindDeleteFirebaseDatabase(deleteFirebaseDatabaseImpl: DeleteFirebaseDatabaseImpl): DeleteFirebaseDatabase
}