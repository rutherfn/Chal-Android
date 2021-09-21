package com.nicholasrutherford.chal.firebase.storage.di

import com.nicholasrutherford.chal.firebase.storage.ChalFirebaseStorage
import com.nicholasrutherford.chal.firebase.storage.ChalFirebaseStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ChalFirebaseStorageModule {

    @Binds
    abstract fun bindChalFirebaseStorage(chalFirebaseStorageImpl: ChalFirebaseStorageImpl): ChalFirebaseStorage
}