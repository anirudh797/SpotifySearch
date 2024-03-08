package com.anirudh.spotifysearch.di.module

import androidx.lifecycle.ViewModelProvider
import com.anirudh.spotifysearch.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
public abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}