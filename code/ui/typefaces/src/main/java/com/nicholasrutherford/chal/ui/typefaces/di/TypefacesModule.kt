package com.nicholasrutherford.chal.ui.typefaces.di

import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class TypefacesModule {

    @Binds
    abstract fun bindTypefaces(typefacesImpl: TypefacesImpl): Typefaces
}