package com.anirudh.spotifysearch.data.model

import java.io.Serializable

data class Album(
    val album_type: String,
    val artists: List<Artist>,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<Image>,
    val is_playable: Boolean,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
) : Serializable