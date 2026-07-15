package com.martinez.appitasset.di

import com.martinez.appitasset.presentation.viewmodel.AddViewModel
import com.martinez.appitasset.presentation.viewmodel.RegisterViewModel
import com.martinez.appitasset.presentation.viewmodel.SearchViewModel

class AppContainer {

    private val networkModule by lazy { NetworkModule() }
    private val repositoryModule by lazy { RepositoryModule(networkModule) }
    private val useCaseModule by lazy { UseCaseModule(repositoryModule) }

    val addViewModel by lazy {
        AddViewModel()
    }
    val searchViewModel by lazy {
        SearchViewModel()
    }
    val registerViewModel by lazy {
        RegisterViewModel(useCaseModule.itAssetUseCases)
    }
}