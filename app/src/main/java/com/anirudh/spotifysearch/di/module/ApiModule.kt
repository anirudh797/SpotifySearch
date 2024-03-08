package com.anirudh.spotifysearch.di.module

import com.anirudh.spotifysearch.data.remote.AccountsApi
import com.anirudh.spotifysearch.data.remote.SpotifyApi
import com.anirudh.upstox.data.remote.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    /*
   * The method returns the Gson object
   * */
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    @Provides
    @Singleton
    fun provideSearchApiService(gson: Gson): SpotifyApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(RetrofitInstance.BASE_URL)
            .build().create(SpotifyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountsApiService(gson: Gson): AccountsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(RetrofitInstance.ACCOUNTS_BASE_URL)
            .build().create(AccountsApi::class.java)
    }

}