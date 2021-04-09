package com.nicholasrutherford.chal.dagger.modules

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.dagger.ViewModelKey
import com.nicholasrutherford.chal.peoplelist.PeopleListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PeopleListViewModel::class)
    internal abstract fun bindMyViewModel(viewModel: PeopleListViewModel): ViewModel
}
