package com.anirudh.spotifysearch.data.model

data class Playlists(
    val href: String,
    val items: List<PlaylistItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)