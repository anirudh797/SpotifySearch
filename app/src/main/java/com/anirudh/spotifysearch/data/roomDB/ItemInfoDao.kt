package com.anirudh.spotifysearch.data.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anirudh.spotifysearch.data.model.ItemInfo

@Dao
interface ItemInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertItemInfo(item: EntityItemInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertItems(items: List<EntityItemInfo>)

//    @Query("SELECT * FROM item_info WHERE id=:id")
//    suspend fun getItemById(id: Int): LiveData<EntityItemInfo>

    @Query("SELECT * FROM item_info")
     fun getAllItems(): List<EntityItemInfo>

    @Query("DELETE FROM item_info")
     fun clearDb()
}

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImage(image: ImageEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(items: List<ImageEntity>): Int

    @Query("SELECT * FROM image_entity WHERE id=:id")
    suspend fun getImageById(id: Int): ImageEntity?

    @Query("SELECT * FROM image_entity")
    suspend fun getAllImages(id: Int): List<ImageEntity>

}