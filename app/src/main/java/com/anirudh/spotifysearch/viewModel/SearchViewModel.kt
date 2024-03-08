package com.anirudh.spotifysearch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.spotifysearch.data.SearchRepository
import com.anirudh.spotifysearch.data.model.AlbumItem
import com.anirudh.spotifysearch.data.model.ArtistInfo
import com.anirudh.spotifysearch.data.model.CategoryType
import com.anirudh.spotifysearch.data.model.PlaylistItem
import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TrackItem
import com.anirudh.spotifysearch.data.model.Tracks
import com.anirudh.spotifysearch.util.Constants
import com.anirudh.upstox.data.remote.RetrofitInstance
import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Reusable
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var _loadingProgressLiveData = MutableLiveData(false)
    var loadingProgressLiveData: LiveData<Boolean> = _loadingProgressLiveData

    //
    private var _searchResults = MutableLiveData<SearchResults?>()
    val searchResults: MutableLiveData<SearchResults?> = _searchResults

    private var _categories = MutableLiveData<List<CategoryType>>()
    val categories: MutableLiveData<List<CategoryType>> = _categories

//    private var _playlistItems = MutableLiveData<List<PlaylistItem>>()
//    val playlistItems: LiveData<List<PlaylistItem>> = _playlistItems
//
//    private var _tracks = MutableLiveData<List<TrackItem>>()
//    val tracks: LiveData<List<TrackItem>> = _tracks
//
//    private var _albumItems = MutableLiveData<List<AlbumItem>>()
//    val albumItems: LiveData<List<AlbumItem>> = _albumItems
//
//    private var _artistsInfo = MutableLiveData<List<ArtistInfo>>()
//    val artistsInfo: LiveData<List<ArtistInfo>> = _artistsInfo
//
    fun getSearchResults(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _loadingProgressLiveData.postValue(true)
            val result = searchRepository.getAllSearchResults(query = query)
            if (result.isSuccessful) {
                _loadingProgressLiveData.postValue(false)
                val results = result.body()
                if (results != null) {
                    prepareCategoriesList(results)
                }
                _searchResults.postValue(results)
            } else {
                _loadingProgressLiveData.postValue(false)
            }
        }
    }

    private fun prepareCategoriesList(results: SearchResults) {
        val categoriesList: ArrayList<CategoryType> = arrayListOf()
        results.let {
//            when {
            if (results.albums.albumItems.isNotEmpty()) {
                categoriesList.add(CategoryType.Albums(Constants.ALBUMS))
            }

            if (results.tracks.items.isNotEmpty()) {
                categoriesList.add(CategoryType.Tracks(Constants.TRACKS))

            }

            if (results.artists.items.isNotEmpty()) {
                categoriesList.add(CategoryType.Artists(Constants.ARTISTS))

            }

            if (results.playlists.items.isNotEmpty()) {
                categoriesList.add(CategoryType.Playlists(Constants.PLAYLISTS))

            }
        }
        _categories.postValue(categoriesList)

    }

    fun updateApiAccessToken() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchRepository.getApiAccessToken()
            if (result.isSuccessful) {
                Constants.token = result.body()?.access_token.toString()
            } else {
                //nothing
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun updateCategoriesData() {
//        _searchResults.value.let {
//            _albumItems.postValue(it?.albums?.albumItems)
//            _tracks.postValue(it?.tracks?.items)
//            _playlistItems.postValue(it?.playlists?.items)
//            _artistsInfo.postValue(it?.artists?.items)
//        }
    }


}