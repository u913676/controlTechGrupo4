package com.jnarvaez.appsemana03.di

import com.jnarvaez.appsemana03.core.network.ApiService
import com.jnarvaez.appsemana03.core.network.RetrofitClient

class NetworkModule {
    val apiService: ApiService by lazy {
        RetrofitClient.api
    }
}
