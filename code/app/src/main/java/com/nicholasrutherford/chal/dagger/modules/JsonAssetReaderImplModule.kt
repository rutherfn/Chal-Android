package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.helpers.jsonAssetReader.JsonAssetReaderImpl
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class JsonAssetReaderImplModule {

    @ContributesAndroidInjector
    abstract fun contributeJsonAssetReaderImpl() : JsonAssetReaderImpl

    @Binds
    abstract fun bindTypeface(service: TypefacesImpl): Typefaces
}