package com.anirudh.spotifysearch.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.anirudh.spotifysearch.DetailActivity
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.ui.adapters.SearchResultsAdapter
import com.anirudh.spotifysearch.util.Constants

import com.anirudh.spotifysearch.viewModel.SearchViewModel

class PlaylistsFragment(val vm: SearchViewModel) : SearchResultsFragment() {

    lateinit var adapter: SearchResultsAdapter

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
        adapter = SearchResultsAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(Constants.ITEM_INFO, it)
            startActivity(intent)
        }
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.searchResults.observe(viewLifecycleOwner) {
            it?.playlists?.items?.let { items -> adapter.updateList(items) }
        }
    }

    override fun getResultsAdapter(): SearchResultsAdapter {
        return adapter
    }

}
