package com.android.example.wallpaper.models.pixabay

data class ReqPhoto(
    val key: String,
    val q: String,
    val image_type: String,
    val pretty: Boolean
)