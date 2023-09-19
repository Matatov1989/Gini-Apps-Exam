package com.example.giniappsexam.fragments.gallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giniappsexam.model.PixabayImage
import com.example.giniappsexam.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: GalleryRepository) :
    ViewModel() {

    val imagesLiveData = MutableLiveData<List<PixabayImage>>()

    private val cartProducts: Flow<List<PixabayImage>> = flow {
        try {
            repository.getImagesFromLocalStorage().distinctUntilChanged().collect { list ->
                list.sortedByDescending { it.likes }
                if (list.isNotEmpty())
                    emit(list)
            }
        } catch (error: Throwable) {
            throw error
        }
    }.flowOn(Dispatchers.IO)


    fun fetchImages() {
        viewModelScope.launch(Dispatchers.IO) {
            cartProducts.collect {
                imagesLiveData.postValue(it)
            }
        }
    }


    fun loadImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImagesFromApi()

            if (response.isSuccessful) {
                val size = repository.getTableSize()
                if (size == 0)
                    repository.insertAllToLocalStorage(response.body()?.hits!!)
                else if (size > 0)
                    repository.updateAllToLocalStorage(response.body()?.hits!!)
            } else {
                Log.e("ERROR", "response is ${response.isSuccessful}")
            }
        }
    }
}