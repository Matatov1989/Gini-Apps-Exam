package com.example.giniappsexam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.giniappsexam.model.PixabayImage
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryDao {

    @Query("SELECT * FROM GalleryTable WHERE likes > 50 AND comments > 50 ORDER BY likes DESC")
    fun getImages(): Flow<List<PixabayImage>>

    @Insert
    suspend fun insertAll(list: List<PixabayImage>)

    @Update
    suspend fun updateAll(list: List<PixabayImage>)

    @Query("SELECT COUNT(id) FROM GalleryTable")
    suspend fun getTableSize(): Int
}