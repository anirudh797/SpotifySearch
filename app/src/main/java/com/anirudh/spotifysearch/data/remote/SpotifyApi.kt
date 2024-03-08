package com.anirudh.spotifysearch.data.remote

import com.anirudh.spotifysearch.data.model.AlbumDetails
import com.anirudh.spotifysearch.data.model.ArtistDetail
import com.anirudh.spotifysearch.data.model.PlaylistDetail
import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TokenApiResponse
import com.anirudh.spotifysearch.data.model.TrackInfo
import com.anirudh.spotifysearch.util.Constants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApi {

    companion object {
        const val TYPE = "album,artist,playlist,track"
        const val MARKET = "ES"
        const val LIMIT = 10
        const val OFFSET = 0
        var ACCESS_TOKEN = "Bearer ${Constants.token}"
        const val CLIENT_ID = "d7294c9a8bf94eb2b0501a5a81dc22e1"
        const val CLIENT_SECRET = "6cc10fb3198844e9b4121e63d0d638c3"
    }

    @GET("v1/search")
    suspend fun getSearchResults(
        @Header("Authorization") accessToken: String = ACCESS_TOKEN,
        @Query("q") query: String,
        @Query("type") type: String = TYPE,
        @Query("market") market: String = MARKET,
        @Query("limit") limit: Int = LIMIT,
        @Query("offset") offset: Int = 0
    ): Response<SearchResults>


    @GET("v1/albums/{id}")
    suspend fun getAlbumDetails(
        @Header("Authorization") accessToken: String = ACCESS_TOKEN,
        @Path("id") id: String,
        @Query("market") market: String = MARKET,
    ): Response<AlbumDetails>


    @GET("v1/artists/{id}")
    suspend fun getArtistDetails(
        @Header("Authorization") accessToken: String = ACCESS_TOKEN,
        @Path("id") id: String,
        @Query("market") market: String = MARKET,
    ): Response<ArtistDetail>

    @GET("v1/tracks/{id}")
    suspend fun getTrackDetails(
        @Header("Authorization") accessToken: String = ACCESS_TOKEN,
        @Path("id") id: String,
        @Query("market") market: String = MARKET,
    ): Response<TrackInfo>

    @GET("v1/playlists/{id}")
    suspend fun getPlaylistDetails(
        @Header("Authorization") accessToken: String = ACCESS_TOKEN,
        @Path("id") id: String,
        @Query("market") market: String = MARKET,
    ): Response<PlaylistDetail>
}

interface AccountsApi {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded",
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String = SpotifyApi.CLIENT_ID,
        @Field("client_secret") clientSecret: String = SpotifyApi.CLIENT_SECRET
    ): Response<TokenApiResponse>
}