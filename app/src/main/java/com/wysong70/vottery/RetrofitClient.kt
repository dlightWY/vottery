package com.wysong70.vottery

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(SERVER_ADD)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance!!
    }
}
