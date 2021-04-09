package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AdapterModule::class, FragmentBuildersModule::class, NavigationImplModule::class,  ViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}
