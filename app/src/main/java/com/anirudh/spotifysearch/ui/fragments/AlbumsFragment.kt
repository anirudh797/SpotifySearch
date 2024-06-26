package com.anirudh.spotifysearch.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.anirudh.spotifysearch.ui.DetailActivity
import com.anirudh.spotifysearch.ui.adapters.SearchResultsAdapter
import com.anirudh.spotifysearch.util.Constants


class AlbumsFragment : SearchResultsFragment() {

    private lateinit var adapter: SearchResultsAdapter

    companion object {
        fun newInstance(): AlbumsFragment {
            return AlbumsFragment()
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
            it?.albums?.albumItems?.let { items -> adapter.updateList(items) }
        }
    }

    override fun getResultsAdapter(): SearchResultsAdapter {
        return adapter
    }

    override fun onDestroyView() {
        Log.d("Anirudh","onDestroy View Albums")
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyOptionsMenu() {
        Log.d("Anirudh","onDestroy optionsMenu Albums")
        super.onDestroyOptionsMenu()
    }
}
