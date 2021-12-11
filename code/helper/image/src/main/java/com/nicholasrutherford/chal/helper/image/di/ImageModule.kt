package com.nicholasrutherford.chal.helper.image.di

import com.nicholasrutherford.chal.helper.image.Image
import com.nicholasrutherford.chal.helper.image.ImageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ImageModule {

    @Binds
    abstract fun bindImage(imageImpl: ImageImpl): Image
}