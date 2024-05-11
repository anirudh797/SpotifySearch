package com.anirudh.spotifysearch.di.module

import com.anirudh.spotifysearch.ui.fragments.AlbumsFragment
import com.anirudh.spotifysearch.ui.fragments.ArtistsFragment
import com.anirudh.spotifysearch.ui.fragments.MainFragment
import com.anirudh.spotifysearch.ui.fragments.PlaylistsFragment
import com.anirudh.spotifysearch.ui.fragments.SearchResultsFragment
import com.anirudh.spotifysearch.ui.fragments.TracksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeAlbumsFragment(): AlbumsFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributePlayListFragment(): PlaylistsFragment

    @ContributesAndroidInjector
    abstract fun contributeTracksFragment(): TracksFragment

    @ContributesAndroidInjector
    abstract fun contributeArtistsFragment(): ArtistsFragment


}