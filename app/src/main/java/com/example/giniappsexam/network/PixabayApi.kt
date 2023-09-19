package com.example.giniappsexam.network

import com.example.giniappsexam.model.PixabayResponse
import com.example.giniappsexam.util.Constants.PIXABAY_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun loadImages(
        @Query("key") apiKey: String = PIXABAY_API_KEY
    ): Response<PixabayResponse>
}