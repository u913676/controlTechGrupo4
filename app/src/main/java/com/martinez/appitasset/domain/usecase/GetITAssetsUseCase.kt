package com.martinez.appitasset.domain.usecase

import com.martinez.appitasset.domain.repository.ITAssetRepository

class GetITAssetsUseCase(private val repository: ITAssetRepository) {
    suspend operator fun invoke() = repository.getITAssets()
}