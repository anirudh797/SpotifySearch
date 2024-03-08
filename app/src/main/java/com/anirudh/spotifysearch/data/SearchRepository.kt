package com.anirudh.spotifysearch.data

import com.anirudh.spotifysearch.data.model.AlbumDetails
import com.anirudh.spotifysearch.data.model.ArtistDetail
import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TokenApiResponse
import com.anirudh.spotifysearch.data.remote.AccountsApi
import com.anirudh.spotifysearch.data.remote.SpotifyApi
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val spotifyApi: SpotifyApi,
    private val accountsApi: AccountsApi
) : ISearchRepository {
    override suspend fun getAllSearchResults(query: String): Response<SearchResults> {
        return spotifyApi.getSearchResults(query = query)
    }

    override suspend fun getAlbumDetails(id: String): Response<AlbumDetails> {
        return spotifyApi.getAlbumDetails(id = id)
    }

    override suspend fun getArtistDetails(id: String): Response<ArtistDetail> {
        return spotifyApi.getArtistDetails(id = id)
    }


    override suspend fun getApiAccessToken(): Response<TokenApiResponse> {
        return accountsApi.getAccessToken()
    }


}