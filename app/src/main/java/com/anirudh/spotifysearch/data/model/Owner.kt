package com.anirudh.spotifysearch.data.model

import java.io.Serializable

data class Owner(
    val display_name: String,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
) : Serializable