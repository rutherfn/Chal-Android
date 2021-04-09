package com.nicholasrutherford.chal.dagger.modules

import com.nicholasrutherford.chal.peoplelist.PeopleListAdapter
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AdapterModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleListAdapter(): PeopleListAdapter
}
