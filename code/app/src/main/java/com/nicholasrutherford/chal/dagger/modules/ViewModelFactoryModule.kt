package com.nicholasrutherford.chal.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.nicholasrutherford.chal.dagger.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
