package com.anirudh.spotifysearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.anirudh.spotifysearch.databinding.FragmentSearchResultsBinding
import com.anirudh.spotifysearch.ui.adapters.SearchResultsAdapter
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class SearchResultsFragment : Fragment() {

    @Inject
    lateinit var searchViewModelFactory: ViewModelProvider.Factory

    lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        searchViewModel = getViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    private fun getViewModel(): SearchViewModel {
        searchViewModel =
            activity?.viewModelStore?.let { ViewModelProvider(it, searchViewModelFactory) }?.get(SearchViewModel::class.java) ?:
                    ViewModelProvider(this, searchViewModelFactory)[SearchViewModel::class.java]

        return searchViewModel
    }

    fun getBinding(): FragmentSearchResultsBinding {
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.apply {
            adapter = getResultsAdapter()
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun getResultsAdapter(): SearchResultsAdapter

}
