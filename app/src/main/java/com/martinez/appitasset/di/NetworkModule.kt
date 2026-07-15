package com.martinez.appitasset.di

import com.martinez.appitasset.core.network.ApiService
import com.martinez.appitasset.core.network.RetrofitClient

class NetworkModule {
    val apiService: ApiService by lazy {
        RetrofitClient.api
    }
}