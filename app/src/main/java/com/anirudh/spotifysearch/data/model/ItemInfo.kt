package com.anirudh.spotifysearch.data.model

import android.os.Parcelable
import java.io.Serializable

interface ItemInfo : Serializable
data class AlbumItem(
    val album_type: String,
    val artists: List<Artist>,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val is_playable: Boolean,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String,
    val itemType: ItemType = ItemType.ALBUM
) : ItemInfo, Serializable

enum class ItemType : Serializable {
    ALBUM, TRACK, PLAYLIST, ARTIST
}

data class PlaylistItem(
    val collaborative: Boolean,
    val description: String,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val owner: Owner,
    val primary_color: Any,
    val `public`: Any,
    val snapshot_id: String,
    val playlistTracks: PlaylistTracks,
    val type: String,
    val uri: String,
    val itemType: ItemType = ItemType.PLAYLIST
) : ItemInfo, Serializable

data class ArtistInfo(
    val external_urls: ExternalUrlsX,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String,
    val itemType: ItemType = ItemType.ARTIST
) : ItemInfo, Serializable

data class TrackItem(
    val album: Album,
    val artists: List<Artist>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val name: String,
    val popularity: Int,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String,
    val itemType: ItemType = ItemType.TRACK
) : ItemInfo, Serializable
