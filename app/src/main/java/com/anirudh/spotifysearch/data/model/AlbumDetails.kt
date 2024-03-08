package com.anirudh.spotifysearch.data.model

data class AlbumDetails(
    val album_type: String,
    val artists: List<Artist>,
    val copyrights: List<Copyright>,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrlsX,
    val genres: List<Any>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val is_playable: Boolean,
    val label: String,
    val name: String,
    val popularity: Int,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: Tracks,
    val type: String,
    val uri: String
)