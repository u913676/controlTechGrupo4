package com.jnarvaez.appsemana03.di

import com.jnarvaez.appsemana03.data.remote.datasource.RemoteDataSource
import com.jnarvaez.appsemana03.data.repository.EquipoRepositoryImpl
import com.jnarvaez.appsemana03.domain.repository.EquipoRepository

class RepositoryModule(networkModule: NetworkModule) {
    private val remoteDataSource by lazy {
        RemoteDataSource(networkModule.apiService)
    }

    val equipoRepository: EquipoRepository by lazy {
        EquipoRepositoryImpl(remoteDataSource)
    }
}
