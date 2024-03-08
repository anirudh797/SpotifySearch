package com.anirudh.spotifysearch.data.model

data class Item(
    val added_at: String,
    val added_by: AddedBy,
    val is_local: Boolean,
    val track: Track
)