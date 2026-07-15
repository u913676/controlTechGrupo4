package com.martinez.appitasset.di

import com.martinez.appitasset.domain.usecase.AddITAssetUseCase
import com.martinez.appitasset.domain.usecase.DeleteITAssetUseCase
import com.martinez.appitasset.domain.usecase.GetITAssetUseCase
import com.martinez.appitasset.domain.usecase.GetITAssetsUseCase
import com.martinez.appitasset.domain.usecase.ITAssetUseCases
import com.martinez.appitasset.domain.usecase.UpdateITAssetUseCase

class UseCaseModule(repositoryModule: RepositoryModule) {
    val itAssetUseCases by lazy {
        ITAssetUseCases(
            GetITAssetsUseCase(repositoryModule.itAssetRepository),
            AddITAssetUseCase(repositoryModule.itAssetRepository),
            GetITAssetUseCase(repositoryModule.itAssetRepository),
            UpdateITAssetUseCase(repositoryModule.itAssetRepository),
            DeleteITAssetUseCase(repositoryModule.itAssetRepository)
        )
    }
}