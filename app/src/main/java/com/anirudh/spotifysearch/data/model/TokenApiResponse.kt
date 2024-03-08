package com.anirudh.spotifysearch.data.model

data class TokenApiResponse(
    val access_token: String,
    val expires_in: Int,
    val token_type: String
)