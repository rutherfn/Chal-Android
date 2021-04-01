package com.nicholasrutherford.chal

import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleListFragment(): PeopleListFragment
}
