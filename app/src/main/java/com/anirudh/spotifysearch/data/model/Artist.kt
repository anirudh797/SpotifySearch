package com.anirudh.spotifysearch.data.model

import java.io.Serializable

data class Artist(
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
):Serializable