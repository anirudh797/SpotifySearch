package com.anirudh.spotifysearch.data.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.anirudh.spotifysearch.data.model.ItemType

@Entity(tableName = "item_info")
@TypeConverters(Converters::class)
data class EntityItemInfo(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "itemType")
    var itemType: ItemType
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemId")
    var itemId: Int = 0
}