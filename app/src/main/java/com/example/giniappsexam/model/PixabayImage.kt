package com.example.giniappsexam.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GalleryTable")
data class PixabayImage(
    @PrimaryKey
    val id: Long,
    val largeImageURL: String? = null,
    val likes: Int,
    val comments: Int
)
