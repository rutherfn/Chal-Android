package com.nicholasrutherford.chal.di

import com.nicholasrutherford.chal.Network
import com.nicholasrutherford.chal.NetworkImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindNetwork(networkImpl: NetworkImpl): Network
}