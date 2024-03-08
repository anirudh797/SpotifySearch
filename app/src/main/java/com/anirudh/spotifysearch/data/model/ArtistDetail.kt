package com.anirudh.spotifysearch.data.model

data class ArtistDetail(
    val external_urls: ExternalUrlsX,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)