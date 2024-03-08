package com.anirudh.spotifysearch.data.model

import com.google.gson.annotations.SerializedName

data class Albums(
    val href: String,
    @SerializedName("items")
    val albumItems: List<AlbumItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)