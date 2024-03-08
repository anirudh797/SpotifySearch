package com.anirudh.spotifysearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anirudh.spotifysearch.SearchViewModelFactory
import com.anirudh.spotifysearch.data.SearchRepository
import com.anirudh.spotifysearch.databinding.FragmentSearchResultsBinding
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import com.anirudh.upstox.data.remote.RetrofitInstance


abstract class SearchResultsFragment : Fragment() {

    //    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory

    lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
//        val viewModelProviderFactory =
//            ViewModelProviderFactory(
//                SearchRepository(
//                    RetrofitInstance.api,
//                    RetrofitInstance.accountsAPi
//                )
//            )
//        searchViewModel =
//            ViewModelProvider(this, viewModelProviderFactory)[SearchViewModel::class.java]
        return searchViewModel
    }

    fun getBinding(): FragmentSearchResultsBinding {
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    abstract fun getAdapter(): Adapter<RecyclerView.Adapter<>>
}
