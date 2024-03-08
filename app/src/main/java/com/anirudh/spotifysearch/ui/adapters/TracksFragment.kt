package com.anirudh.spotifysearch.ui.adapters

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.RecyclerView
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.ui.fragments.SearchResultsFragment


class TracksFragment : SearchResultsFragment() {

    private var adapter: SearchResultsAdapter? = null

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
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchResultsAdapter {

        }
        searchViewModel.tracks.observe(viewLifecycleOwner) {
            adapter?.updateList(it)
        }
    }
}
