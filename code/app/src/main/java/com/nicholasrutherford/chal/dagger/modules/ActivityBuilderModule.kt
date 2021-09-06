package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            AdapterModule::class,
            FragmentBuildersModule::class,
            JsonAssetReaderImplModule::class,
            NavigationImplModule::class,
            SharedPreferenceModule::class,
            TestFairyModule::class,
            ViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
