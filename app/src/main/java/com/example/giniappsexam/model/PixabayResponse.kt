package com.example.giniappsexam.model

data class PixabayResponse(
    val totalHits: Int,
    val hits: List<PixabayImage>
)
