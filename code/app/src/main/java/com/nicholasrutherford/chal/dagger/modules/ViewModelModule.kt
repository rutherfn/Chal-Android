package com.nicholasrutherford.chal.dagger.modules

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.dagger.ViewModelKey
import com.nicholasrutherford.chal.main.MainViewModel
import com.nicholasrutherford.chal.more.MoreViewModel
import com.nicholasrutherford.chal.newsfeed.NewsFeedViewModel
import com.nicholasrutherford.chal.peoplelist.PeopleListViewModel
import com.nicholasrutherford.chal.profile.editprofile.EditProfileViewModel
import com.nicholasrutherford.chal.profile.profiles.MyProfileViewModel
import com.nicholasrutherford.chal.progressupload.ProgressUploadViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    internal abstract fun bindEditProfileViewModel(viewModel: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(vieModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel::class)
    internal abstract fun bindMoreViewModel(viewModel: MoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyProfileViewModel::class)
    internal abstract fun bindMyProfileViewModel(viewModel: MyProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    internal abstract fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PeopleListViewModel::class)
    internal abstract fun bindPeopleListViewModel(viewModel: PeopleListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProgressUploadViewModel::class)
    internal abstract fun bindProgressUploadViewModel(viewModel: ProgressUploadViewModel): ViewModel
}
