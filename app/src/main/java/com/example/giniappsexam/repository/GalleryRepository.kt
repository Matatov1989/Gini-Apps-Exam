package com.example.giniappsexam.repository

import com.example.giniappsexam.data.GalleryDao
import com.example.giniappsexam.model.PixabayImage
import com.example.giniappsexam.model.PixabayResponse
import com.example.giniappsexam.network.PixabayApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val api: PixabayApi,
    private val galleryDao: GalleryDao
){

    fun getImagesFromLocalStorage(): Flow<List<PixabayImage>> = galleryDao.getImages()

    suspend fun insertAllToLocalStorage(list: List<PixabayImage>) = galleryDao.insertAll(list)

    suspend fun updateAllToLocalStorage(list: List<PixabayImage>) = galleryDao.updateAll(list)

    suspend fun getTableSize() = galleryDao.getTableSize()

    suspend fun getImagesFromApi(): Response<PixabayResponse> = api.loadImages()
}