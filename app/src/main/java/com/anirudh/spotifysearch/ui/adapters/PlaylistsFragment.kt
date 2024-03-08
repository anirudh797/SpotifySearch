package com.anirudh.spotifysearch.ui.adapters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.anirudh.spotifysearch.data.model.ItemInfo

import com.anirudh.spotifysearch.ui.fragments.SearchResultsFragment
import com.anirudh.spotifysearch.viewModel.SearchViewModel

class PlaylistsFragment(val vm: SearchViewModel) : SearchResultsFragment() {

    private var adapter: SearchResultsAdapter? = null

    companion object {
        fun newInstance(searchViewModel: SearchViewModel): PlaylistsFragment {
            return PlaylistsFragment(searchViewModel)
        }
    }

    private fun onPlayListClick(): (ItemInfo) -> Unit {
        return {

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchResultsAdapter {

        }
        getBinding().rv.adapter = adapter
        vm.playlistItems.observe(viewLifecycleOwner) {
            adapter?.updateList(it)
        }
    }
}
