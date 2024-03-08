package com.anirudh.spotifysearch.di.module

import com.anirudh.spotifysearch.ui.DetailActivity
import com.anirudh.spotifysearch.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeDetailActivity(): DetailActivity
}