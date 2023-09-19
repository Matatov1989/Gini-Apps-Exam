package com.example.giniappsexam.service

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.giniappsexam.data.GalleryDao
import com.example.giniappsexam.network.PixabayApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateDataWorker @Inject constructor(
    context: Context,
    params: WorkerParameters,
    private val api: PixabayApi,
    private val galleryDao: GalleryDao
) : Worker(context, params) {

    override fun doWork(): Result {
        return try {

            CoroutineScope(Dispatchers.IO).launch {
                val response = api.loadImages()

                if (response.isSuccessful) {
                    galleryDao.updateAll(response.body()?.hits!!)
                } else {
                    Log.e("ERROR", "jop service response is ${response.isSuccessful}")
                }
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}