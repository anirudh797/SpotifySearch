package com.anirudh.spotifysearch.data

import com.anirudh.spotifysearch.data.model.AlbumDetails
import com.anirudh.spotifysearch.data.model.ArtistDetail
import com.anirudh.spotifysearch.data.model.PlaylistDetail
import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TokenApiResponse
import com.anirudh.spotifysearch.data.model.TrackInfo
import retrofit2.Response

interface ISearchRepository {
    suspend fun getAllSearchResults(
        query: String,
    ): Response<SearchResults>

    suspend fun getAlbumDetails(
        id: String,
    ): Response<AlbumDetails>

    suspend fun getArtistDetails(
        id: String,
    ): Response<ArtistDetail>

    suspend fun getPlaylistDetails(
        id: String,
    ): Response<PlaylistDetail>

    suspend fun getTrackDetails(
        id: String,
    ): Response<TrackInfo>

    suspend fun getApiAccessToken(): Response<TokenApiResponse>
}