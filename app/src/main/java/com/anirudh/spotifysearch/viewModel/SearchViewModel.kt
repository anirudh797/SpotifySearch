package com.anirudh.spotifysearch.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.spotifysearch.data.SearchRepository
import com.anirudh.spotifysearch.data.model.AlbumDetails
import com.anirudh.spotifysearch.data.model.AlbumItem
import com.anirudh.spotifysearch.data.model.ArtistDetail
import com.anirudh.spotifysearch.data.model.ArtistInfo
import com.anirudh.spotifysearch.data.model.CategoryType
import com.anirudh.spotifysearch.data.model.PlaylistDetail
import com.anirudh.spotifysearch.data.model.PlaylistItem
import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TrackInfo
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

    private var _searchResults = MutableLiveData<SearchResults?>()
    val searchResults: MutableLiveData<SearchResults?> = _searchResults

    private var _categories = MutableLiveData<List<CategoryType>>()
    val categories: MutableLiveData<List<CategoryType>> = _categories

    private var _playlistItems = MutableLiveData<PlaylistDetail>()
    val playlistDetail: LiveData<PlaylistDetail> = _playlistItems

    private var _trackDetail = MutableLiveData<TrackInfo>()
    val trackDetail: LiveData<TrackInfo> = _trackDetail

    private var _albumDetails = MutableLiveData<AlbumDetails>()
    val albumDetail: LiveData<AlbumDetails> = _albumDetails

    private var _artistsInfo = MutableLiveData<ArtistDetail>()
    val artistsInfo: LiveData<ArtistDetail> = _artistsInfo

    fun getSearchResults(query: String) {
        Log.d("Anirudh", "Outside coroutines")
        viewModelScope.launch(Dispatchers.Default) {
            Log.d("Anirudh", "$query search")
            _loadingProgressLiveData.postValue(true)
            val result = searchRepository.getAllSearchResults(query = query)
            if (result.isSuccessful) {
                _loadingProgressLiveData.postValue(false)
                val results = result.body()
                if (results != null) {
                    prepareCategoriesList(results)
                }
                _searchResults.postValue(results)
                Log.d("Anirudh", "$query success")
            } else {
                _loadingProgressLiveData.postValue(false)
                Log.d("Anirudh", "$query failure")
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

    fun getAlbumDetails(albumId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchRepository.getAlbumDetails(albumId)
            if (result.isSuccessful) {
                _albumDetails.postValue(result.body())
            } else {
                //nothing
            }
        }
    }

    fun getArtistDetails(artistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchRepository.getArtistDetails(artistId)
            if (result.isSuccessful) {
                _artistsInfo.postValue(result.body())
            } else {
                //nothing
            }
        }
    }

    fun getPlaylistDetails(playlistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchRepository.getPlaylistDetails(playlistId)
            if (result.isSuccessful) {
                _playlistItems.postValue(result.body())
            } else {
                //nothing
            }
        }
    }

    fun getTrackDetails(playlistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchRepository.getTrackDetails(playlistId)
            if (result.isSuccessful) {
                _trackDetail.postValue(result.body())
            } else {
                //nothing
            }
        }
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

}