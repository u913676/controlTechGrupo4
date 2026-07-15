package com.martinez.appitasset.di

import com.martinez.appitasset.data.remote.datasource.RemoteDataSource
import com.martinez.appitasset.data.repository.ITAssetRepositoryImpl
import com.martinez.appitasset.domain.repository.ITAssetRepository

class RepositoryModule(networkModule: NetworkModule) {
    private val remoteDataSource by lazy {
        RemoteDataSource(networkModule.apiService)
    }

    val itAssetRepository : ITAssetRepository by lazy {
        ITAssetRepositoryImpl(remoteDataSource)
    }
}