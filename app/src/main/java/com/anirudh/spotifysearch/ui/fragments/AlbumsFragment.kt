package com.anirudh.spotifysearch.ui.fragments

import android.os.Bundle
import android.view.View
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.ui.adapters.SearchResultsAdapter


class AlbumsFragment : SearchResultsFragment() {

    private lateinit var adapter: SearchResultsAdapter

    companion object {
        fun newInstance(): AlbumsFragment {
            return AlbumsFragment()
        }
    }

    private fun onPlayListClick(): (ItemInfo) -> Unit {
        return {

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SearchResultsAdapter {
        }
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.searchResults.observe(viewLifecycleOwner) {
            it?.albums?.albumItems?.let { items -> adapter.updateList(items) }
        }

    }

    override fun getResultsAdapter(): SearchResultsAdapter {
        return adapter
    }
}
