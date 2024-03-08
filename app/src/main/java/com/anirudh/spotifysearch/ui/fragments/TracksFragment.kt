package com.anirudh.spotifysearch.ui.fragments

import android.os.Bundle
import android.view.View

import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.ui.adapters.SearchResultsAdapter


class TracksFragment : SearchResultsFragment() {

    lateinit var adapter: SearchResultsAdapter

    companion object {
        fun newInstance(): TracksFragment {
            return TracksFragment()
        }
    }

//    override fun getAdapter(): RecyclerView.Adapter {
//        return SearchResultsAdapter(onPlayListClick())
//    }

    private fun onPlayListClick(): (ItemInfo) -> Unit = {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SearchResultsAdapter {
        }
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.searchResults.observe(viewLifecycleOwner) {
            it?.tracks?.items?.let { items -> adapter.updateList(items) }
        }
    }

    override fun getResultsAdapter(): SearchResultsAdapter {
        return adapter
    }
}
