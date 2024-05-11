package com.anirudh.spotifysearch.util

import com.anirudh.spotifysearch.data.model.AlbumItem
import com.anirudh.spotifysearch.data.model.ArtistInfo
import com.anirudh.spotifysearch.data.model.Image
import com.anirudh.spotifysearch.data.model.PlaylistItem
import com.anirudh.spotifysearch.data.model.TrackItem
import com.anirudh.spotifysearch.data.roomDB.EntityItemInfo

fun List<AlbumItem>.toEntityItems(): List<EntityItemInfo> =
    this.map {
        EntityItemInfo(
            id = it.id,
            imageUrl = it.images.first().url,
            name = it.name,
            type = it.type,
            itemType = it.itemType
        )
    }

fun List<EntityItemInfo>.toAlbumItems(): List<AlbumItem> =
    this.map {
        AlbumItem(
            id = it.id,
            images = listOf(Image(0, it.imageUrl, 0)),
            name = it.name,
            type = it.type,
            itemType = it.itemType
        )
    }

fun List<EntityItemInfo>.toTrackItems(): List<TrackItem> =
    this.map {
        TrackItem(
            id = it.id,
            name = it.name,
            type = it.type,
            itemType = it.itemType
        )
    }

fun List<EntityItemInfo>.toArtistItems(): List<ArtistInfo> =
    this.map {
        ArtistInfo(
            id = it.id,
            name = it.name,
            type = it.type,
            images = listOf(Image(0, it.imageUrl, 0)),
            itemType = it.itemType
        )
    }

fun List<EntityItemInfo>.toPlaylistItems(): List<PlaylistItem> =
    this.map {
        PlaylistItem(
            id = it.id,
            name = it.name,
            type = it.type,
            images = listOf(Image(0, it.imageUrl, 0)),
            itemType = it.itemType
        )
    }


fun List<ArtistInfo>.toArtistEntityItems(): List<EntityItemInfo> =
    this.map {
        EntityItemInfo(
            id = it.id,
            imageUrl = if (it.images.isNotEmpty()) {
                it.images.first().url
            } else {
                ""
            },
            name = it.name,
            type = it.type,
            itemType = it.itemType
        )
    }

fun List<TrackItem>.toTrackEntityItems(): List<EntityItemInfo> =
    this.map {
        EntityItemInfo(
            id = it.id,
            imageUrl = "",
            name = it.name,
            type = it.type,
            itemType = it.itemType
        )
    }

fun List<PlaylistItem>.toPlaylistEntityItems(): List<EntityItemInfo> =
    this.map {
        EntityItemInfo(
            id = it.id,
            imageUrl = it.images.first().url,
            name = it.name,
            type = it.type,
            itemType = it.itemType
        )
    }