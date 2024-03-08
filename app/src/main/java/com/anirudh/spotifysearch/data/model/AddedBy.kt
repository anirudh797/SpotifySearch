package com.anirudh.spotifysearch.data.model

data class AddedBy(
    val external_urls: ExternalUrls,
    val followers: Followers,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)