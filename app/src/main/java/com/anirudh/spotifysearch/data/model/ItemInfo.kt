package com.anirudh.spotifysearch.data.model

import java.io.Serializable

interface ItemInfo : Serializable

data class AlbumItem(
    val album_type: String = "",
    val artists: List<Artist> = emptyList(),
    val external_urls: ExternalUrls? = null,
    val href: String = "",
    val id: String = "",
    val images: List<Image> = emptyList(),
    val is_playable: Boolean = false,
    val name: String = "",
    val release_date: String = "",
    val release_date_precision: String = "",
    val total_tracks: Int = 0,
    val type: String = "",
    val uri: String = "",
    val itemType: ItemType = ItemType.ALBUM
) : ItemInfo, Serializable {
}

enum class ItemType : Serializable {
    ALBUM, TRACK, PLAYLIST, ARTIST
}

data class PlaylistItem(
    val collaborative: Boolean = false,
    val description: String = "",
    val external_urls: ExternalUrls? = null,
    val href: String = "",
    val id: String = "",
    val images: List<Image> = emptyList(),
    val name: String = "",
    val owner: Owner = Owner("", ExternalUrls(""), "", "", "", ""),
    val primary_color: Any = "",
    val `public`: Any = "",
    val snapshot_id: String = "",
    val playlistTracks: PlaylistTracks = PlaylistTracks("", 0),
    val type: String = "",
    val uri: String = "",
    val itemType: ItemType = ItemType.PLAYLIST
) : ItemInfo, Serializable

data class ArtistInfo(
    val external_urls: ExternalUrls = ExternalUrls(""),
    val followers: Followers = Followers("", 0),
    val genres: List<String> = emptyList(),
    val href: String = "",
    val id: String = "",
    val images: List<Image> = emptyList(),
    val name: String = "",
    val popularity: Int = 0,
    val type: String = "",
    val uri: String = "",
    val itemType: ItemType = ItemType.ARTIST
) : ItemInfo, Serializable

data class TrackItem(
    val album: Album? = null,
    val artists: List<Artist> = emptyList(),
    val disc_number: Int = 0,
    val duration_ms: Int = 0,
    val explicit: Boolean = false,
    val external_ids: ExternalIds = ExternalIds(""),
    val external_urls: ExternalUrls = ExternalUrls(""),
    val href: String = "",
    val id: String = "",
    val is_local: Boolean = false,
    val is_playable: Boolean = false,
    val name: String = "",
    val popularity: Int = 0,
    val preview_url: String = "",
    val track_number: Int = 0,
    val type: String = "",
    val uri: String = "",
    val itemType: ItemType = ItemType.TRACK
) : ItemInfo, Serializable
