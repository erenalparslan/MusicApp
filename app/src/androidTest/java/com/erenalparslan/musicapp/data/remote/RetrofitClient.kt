package com.erenalparslan.musicapp.data.remote

import com.erenalparslan.musicapp.data.remote.deserializer.TracksDeserializer
import com.erenalparslan.musicapp.data.remote.models.Tracks
import com.erenalparslan.musicapp.di.NetworkModule
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofit: Retrofit by lazy {
        val gson = GsonBuilder().registerTypeAdapter(Tracks::class.java, TracksDeserializer())
            .create()
        Retrofit.Builder()
            .baseUrl(NetworkModule.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(gson))
            .build()
    }
}
