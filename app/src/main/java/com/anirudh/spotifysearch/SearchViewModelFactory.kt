package com.anirudh.spotifysearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anirudh.spotifysearch.data.SearchRepository
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class SearchViewModelFactory  @Inject constructor(
//    private val searchRepository: SearchRepository,
//) : ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return SearchViewModel(
//                searchRepository
//                ) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//}