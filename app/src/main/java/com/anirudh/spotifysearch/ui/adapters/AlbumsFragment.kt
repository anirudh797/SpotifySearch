package com.anirudh.spotifysearch.ui.adapters

import android.os.Bundle
import android.view.View
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.ui.fragments.SearchResultsFragment


class AlbumsFragment : SearchResultsFragment() {

    private var adapter: SearchResultsAdapter? = null

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
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchResultsAdapter {
        }
        searchViewModel.albumItems.observe(viewLifecycleOwner) {
            adapter?.updateList(it)
        }
    }
}
