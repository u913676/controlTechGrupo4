package com.martinez.appitasset.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.martinez.appitasset.core.utils.Constants

object RetrofitClient {
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}