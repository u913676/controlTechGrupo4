package com.jnarvaez.appsemana03.di

import com.jnarvaez.appsemana03.presentation.viewmodel.EditarViewModel
import com.jnarvaez.appsemana03.presentation.viewmodel.EquiposViewModel
import com.jnarvaez.appsemana03.presentation.viewmodel.HistorialViewModel
import com.jnarvaez.appsemana03.presentation.viewmodel.ReparacionViewModel

class AppContainer {
    private val networkModule by lazy { NetworkModule() }
    private val repositoryModule by lazy { RepositoryModule(networkModule) }
    private val useCaseModule by lazy { UseCaseModule(repositoryModule) }

    val equiposViewModel by lazy {
        EquiposViewModel(useCaseModule.equipoUseCases)
    }

    val historialViewModel by lazy {
        HistorialViewModel(useCaseModule.equipoUseCases)
    }

    val editarViewModel by lazy {
        EditarViewModel(useCaseModule.equipoUseCases)
    }

    val reparacionViewModel by lazy {
        ReparacionViewModel(useCaseModule.equipoUseCases)
    }
}
