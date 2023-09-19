package com.example.giniappsexam.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.giniappsexam.model.PixabayImage

@Database(entities = [PixabayImage::class], version = 1, exportSchema = false)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
}