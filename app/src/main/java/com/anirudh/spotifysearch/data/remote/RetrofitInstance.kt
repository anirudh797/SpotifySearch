package com.anirudh.upstox.data.remote

import com.anirudh.spotifysearch.data.remote.AccountsApi
import com.anirudh.spotifysearch.data.remote.SpotifyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://api.spotify.com"

    const val ACCOUNTS_BASE_URL = "https://accounts.spotify.com"

    val api: SpotifyApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(SpotifyApi::class.java)
    }

    val accountsAPi: AccountsApi by lazy {
        Retrofit.Builder().baseUrl(ACCOUNTS_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(AccountsApi::class.java)
    }
}