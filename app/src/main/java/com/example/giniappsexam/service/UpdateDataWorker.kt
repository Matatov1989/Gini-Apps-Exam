package com.example.giniappsexam.service

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.giniappsexam.data.GalleryDao
import com.example.giniappsexam.network.PixabayApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltWorker
class UpdateDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val api: PixabayApi,
    private val galleryDao: GalleryDao
) : Worker(context, params) {

    override fun doWork(): Result {
        return try {

            Log.d("TASK", "doWork")

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