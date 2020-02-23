package com.lakmalz.lznewsapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lakmalz.lznewsapplication.ui.home.HomeViewModel
import com.lakmalz.lznewsapplication.ui.home.customnews.CustomNewsViewModel
import com.lakmalz.lznewsapplication.ui.home.headline.HeadlinesViewModel
import com.lakmalz.lznewsapplication.ui.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HeadlinesViewModel::class)
    internal abstract fun postListViewModel(viewModel: HeadlinesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CustomNewsViewModel::class)
    internal abstract fun postCustomNewsViewModel(viewModel: CustomNewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun postProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun postHomeViewModel(viewModel: HomeViewModel): ViewModel

}