package com.anirudh.spotifysearch.data.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityItemInfo::class], version = 1)
abstract class ItemInfoDb : RoomDatabase() {
    abstract fun ItemInfoDao(): ItemInfoDao

    companion object {
        @Volatile
        private var instance: ItemInfoDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): ItemInfoDb {
            return instance ?: synchronized(LOCK) {
                instance ?: BuildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun BuildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ItemInfoDb::class.java, "itemDB")
                .build()
    }
}