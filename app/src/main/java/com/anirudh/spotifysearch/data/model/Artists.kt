package com.anirudh.spotifysearch.data.model

data class Artists(
    val href: String,
    val items: List<ArtistInfo>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)