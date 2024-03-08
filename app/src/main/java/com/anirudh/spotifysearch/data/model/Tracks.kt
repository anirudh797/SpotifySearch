package com.anirudh.spotifysearch.data.model

data class Tracks(
    val href: String,
    val items: List<TrackItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)