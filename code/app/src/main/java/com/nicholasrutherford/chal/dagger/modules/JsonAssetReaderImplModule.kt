package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.helpers.jsonAssetReader.JsonAssetReaderImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class JsonAssetReaderImplModule {

    @ContributesAndroidInjector
    abstract fun contributeJsonAssetReaderImpl() : JsonAssetReaderImpl
}