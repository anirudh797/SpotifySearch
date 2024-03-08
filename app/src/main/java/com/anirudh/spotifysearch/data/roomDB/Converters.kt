package com.anirudh.spotifysearch.data.roomDB

import androidx.room.TypeConverter
import com.anirudh.spotifysearch.data.model.ItemType

class Converters {

    @TypeConverter
    fun toItemType(value: Int) = enumValues<ItemType>()[value]

    @TypeConverter
    fun fromItemType(value: ItemType) = value.ordinal
}
