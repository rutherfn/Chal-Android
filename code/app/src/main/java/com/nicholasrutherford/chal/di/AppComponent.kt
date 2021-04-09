package com.nicholasrutherford.chal.di

import android.app.Application
import com.nicholasrutherford.chal.dagger.modules.ActivityBuildersModule
import com.nicholasrutherford.chal.dagger.MyApplication
import com.nicholasrutherford.chal.dagger.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
