package com.anirudh.spotifysearch.ui.adapters

import android.os.Bundle
import android.view.View
import com.anirudh.spotifysearch.ui.fragments.SearchResultsFragment


class ArtistsFragment : SearchResultsFragment() {

    private var adapter: SearchResultsAdapter? = null

    companion object {
        fun newInstance(): ArtistsFragment {
            return ArtistsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchResultsAdapter {

        }
        searchViewModel.artistsInfo.observe(viewLifecycleOwner){
            adapter?.updateList(it)
        }
    }
}
