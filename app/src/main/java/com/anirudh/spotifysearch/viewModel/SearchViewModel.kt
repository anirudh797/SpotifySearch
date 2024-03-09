package com.anirudh.spotifysearch.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.spotifysearch.data.SearchRepository
import com.anirudh.spotifysearch.data.model.AlbumDetails
import com.anirudh.spotifysearch.data.model.Albums
import com.anirudh.spotifysearch.data.model.ArtistDetail
import com.anirudh.spotifysearch.data.model.Artists
import com.anirudh.spotifysearch.data.model.CategoryType
import com.anirudh.spotifysearch.data.model.ItemType
import com.anirudh.spotifysearch.data.model.PlaylistDetail
import com.anirudh.spotifysearch.data.model.Playlists
import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TrackInfo
import com.anirudh.spotifysearch.data.model.Tracks
import com.anirudh.spotifysearch.data.roomDB.EntityItemInfo
import com.anirudh.spotifysearch.data.roomDB.ItemInfoDb
import com.anirudh.spotifysearch.util.Constants
import com.anirudh.spotifysearch.util.toAlbumItems
import com.anirudh.spotifysearch.util.toArtistEntityItems
import com.anirudh.spotifysearch.util.toArtistItems
import com.anirudh.spotifysearch.util.toEntityItems
import com.anirudh.spotifysearch.util.toPlaylistEntityItems
import com.anirudh.spotifysearch.util.toPlaylistItems
import com.anirudh.spotifysearch.util.toTrackEntityItems
import com.anirudh.spotifysearch.util.toTrackItems
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

    lateinit var db: ItemInfoDb

    var lastQuery = ""

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
                saveInDb(result.body())
                _searchResults.postValue(results)
                Log.d("Anirudh", "$query success")
            } else {
                _loadingProgressLiveData.postValue(false)
                Log.d("Anirudh", "$query failure")
            }
        }
    }

    fun initializeDb(context: Context) {
        db = ItemInfoDb(context)
    }

    private fun saveInDb(results: SearchResults?) {
        viewModelScope.launch(Dispatchers.IO) {
            db.ItemInfoDao().clearDb()
            val list = mutableListOf<EntityItemInfo>()
            results?.let {
                list.addAll(it.albums.albumItems.toEntityItems())
                list.addAll(it.playlists.items.toPlaylistEntityItems())
                list.addAll(it.tracks.items.toTrackEntityItems())
                list.addAll(it.artists.items.toArtistEntityItems())
            }
            if (list.isNotEmpty()) {
                db.ItemInfoDao().insertItems(items = list)
            }
        }
    }

    private fun fetchFromDb() {
        _loadingProgressLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val items = db.ItemInfoDao().getAllItems()
            val albums = items?.filter {
                it.itemType == ItemType.ALBUM
            }?.toAlbumItems()

            val tracks = items?.filter {
                it.itemType == ItemType.TRACK
            }?.toTrackItems()
            val playlists = items?.filter {
                it.itemType == ItemType.PLAYLIST
            }?.toPlaylistItems()
            val artists = items?.filter {
                it.itemType == ItemType.ARTIST
            }?.toArtistItems()

            val searchResults = albums?.let { Albums("", it, 0, "", 0, 0, 0) }?.let {
                artists?.let { it1 -> Artists("", it1, 0, "", 0, 0, 0) }?.let { it2 ->
                    playlists?.let { it1 -> Playlists("", it1, 0, "", 0, 0, 0) }?.let { it3 ->
                        tracks?.let { it1 -> Tracks("", it1, 0, "", 0, 0, 0) }?.let { it4 ->
                            SearchResults(
                                albums = it,
                                artists = it2, playlists =
                                it3,
                                tracks = it4
                            )
                        }
                    }
                }
            }
            _loadingProgressLiveData.postValue(false)
            if (searchResults != null) {
                prepareCategoriesList(searchResults)
            }
            _searchResults.postValue(searchResults)
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

    fun showLastSearchResults() {
        fetchFromDb()
    }

}