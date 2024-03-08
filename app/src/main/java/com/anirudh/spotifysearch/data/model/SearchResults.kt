package com.anirudh.spotifysearch.data.model

import com.google.gson.annotations.SerializedName

data class SearchResults(
    val albums: Albums,
    val artists: Artists,
    val playlists: Playlists,
    val tracks: Tracks
)

sealed class CategoryType(val key: String) {
    data class Tracks(@SerializedName("tracks") val tracks: String) : CategoryType(tracks)
    data class Playlists(@SerializedName("playlists") val playlists: String) :
        CategoryType(playlists)

    data class Albums(@SerializedName("albums") val albums: String) : CategoryType(albums)
    data class Artists(@SerializedName("artists") val artists: String) : CategoryType(artists)
}