package com.anirudh.spotifysearch.data.remote

import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TokenApiResponse
import com.anirudh.spotifysearch.util.Constants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import kotlin.reflect.typeOf

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




}

interface AccountsApi{
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded",
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String = SpotifyApi.CLIENT_ID,
        @Field("client_secret") clientSecret: String = SpotifyApi.CLIENT_SECRET
    ): Response<TokenApiResponse>
}