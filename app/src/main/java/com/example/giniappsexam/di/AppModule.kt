package com.example.giniappsexam.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.room.Room
import androidx.work.WorkerFactory
import com.example.giniappsexam.data.GalleryDao
import com.example.giniappsexam.data.GalleryDatabase
import com.example.giniappsexam.network.PixabayApi
import com.example.giniappsexam.util.Constants.BASE_PIXABAY_URL
import com.example.giniappsexam.util.Constants.GALLERY_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun pixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .baseUrl(BASE_PIXABAY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGalleryDao(galleryDatabase: GalleryDatabase): GalleryDao =
        galleryDatabase.galleryDao()

    @Singleton
    @Provides
    fun provideGalleryDatabase(@ApplicationContext context: Context): GalleryDatabase =
        Room.databaseBuilder(
            context,
            GalleryDatabase::class.java,
            GALLERY_DATABASE
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUpdateDataWorkerFactory(
        workerFactory: HiltWorkerFactory
    ): WorkerFactory {
        return workerFactory
    }
}