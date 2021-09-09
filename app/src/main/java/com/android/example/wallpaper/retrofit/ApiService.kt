package com.android.example.wallpaper.retrofit

import com.android.example.wallpaper.models.pixabay.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getPhotos(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") imageType: String,
        @Query("pretty") pretty: Boolean
    ): Call<Photo>
}