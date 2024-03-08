package com.anirudh.spotifysearch.data

import com.anirudh.spotifysearch.data.model.SearchResults
import com.anirudh.spotifysearch.data.model.TokenApiResponse
import retrofit2.Response

interface ISearchRepository {
    suspend fun getAllSearchResults(
        query: String,
    ): Response<SearchResults>

    suspend fun getApiAccessToken() : Response<TokenApiResponse>
}