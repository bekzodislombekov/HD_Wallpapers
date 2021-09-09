package com.android.example.wallpaper.models.pixabay

import java.io.Serializable

data class Photo(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
) : Serializable