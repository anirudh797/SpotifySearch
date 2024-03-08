package com.anirudh.spotifysearch.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anirudh.spotifysearch.di.ViewModelKey
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import com.anirudh.spotifysearch.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
public abstract class ViewModelModule {



    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    protected abstract fun searchViewModel(searchViewModel: SearchViewModel): ViewModel


}