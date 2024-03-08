package com.anirudh.spotifysearch.data.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_entity")
data class ImageEntity(
    @ColumnInfo("height")
    val height: Int,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("width")
    val width: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}