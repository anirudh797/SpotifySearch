package com.anirudh.spotifysearch.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anirudh.spotifysearch.data.model.CategoryType
import com.anirudh.spotifysearch.ui.fragments.AlbumsFragment
import com.anirudh.spotifysearch.ui.fragments.ArtistsFragment
import com.anirudh.spotifysearch.ui.fragments.PlaylistsFragment
import com.anirudh.spotifysearch.ui.fragments.TracksFragment
import com.anirudh.spotifysearch.viewModel.SearchViewModel

class CategoryAdapter(
    private val categories: List<CategoryType>,
    private val viewModel: SearchViewModel,
    fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return categories.size
    }

    private val fragmentMap: HashMap<Int, Fragment> = HashMap()

    fun getFragments(): HashMap<Int, Fragment> {
        return fragmentMap
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when (categories[position]) {
            is CategoryType.Albums -> AlbumsFragment.newInstance()
            is CategoryType.Playlists -> PlaylistsFragment.newInstance(viewModel)
            is CategoryType.Tracks -> TracksFragment.newInstance()
            is CategoryType.Artists -> ArtistsFragment.newInstance()
        }
        fragmentMap[position] = fragment
        return fragment
    }
}