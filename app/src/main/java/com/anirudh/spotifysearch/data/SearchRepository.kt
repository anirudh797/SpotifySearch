package com.anirudh.spotifysearch.data

import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TokenApiResponse
import com.anirudh.spotifysearch.data.remote.AccountsApi
import com.anirudh.spotifysearch.data.remote.SpotifyApi
import com.anirudh.upstox.data.remote.RetrofitInstance
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class SearchRepository @Inject constructor(private val spotifyApi: SpotifyApi, private val accountsApi: AccountsApi) : ISearchRepository {
    override suspend fun getAllSearchResults(query: String): Response<SearchResults> {
        return spotifyApi.getSearchResults(query = query)
    }

    override suspend fun getApiAccessToken(): Response<TokenApiResponse> {
        return accountsApi.getAccessToken()
    }


}