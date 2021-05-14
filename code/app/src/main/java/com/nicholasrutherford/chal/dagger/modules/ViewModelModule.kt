package com.nicholasrutherford.chal.dagger.modules

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.dagger.ViewModelKey
import com.nicholasrutherford.chal.newsfeed.NewsFeedViewModel
import com.nicholasrutherford.chal.people.SearchPeopleViewModel
import com.nicholasrutherford.chal.peoplelist.PeopleListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PeopleListViewModel::class)
    internal abstract fun bindPeopleListViewModel(viewModel: PeopleListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    internal abstract fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchPeopleViewModel::class)
    internal abstract fun bindSearchPeopleViewModel(viewModel: SearchPeopleViewModel): ViewModel
}
