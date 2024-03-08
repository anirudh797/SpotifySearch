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
    internal fun provideRetrofitApi(): SpotifyApi {
        val api = RetrofitInstance.api
        return api
    }

    @Provides
    @Singleton
    internal fun provideAccountsApi(): AccountsApi {
        val api = RetrofitInstance.accountsAPi
        return api
    }

    @Provides
    @Singleton
    internal fun provideRetrofitSearch(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(RetrofitInstance.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitAccount(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(RetrofitInstance.ACCOUNTS_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchApiService(retrofit: Retrofit): SpotifyApi? {
        return retrofit.create(SpotifyApi::class.java)
    }


    @Provides
    @Singleton
    fun provideAccountsApiService(retrofit: Retrofit): AccountsApi? {
        return retrofit.create(AccountsApi::class.java)
    }

}