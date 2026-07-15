package com.martinez.appitasset.domain.usecase

import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.repository.ITAssetRepository

class UpdateITAssetUseCase(private val repository: ITAssetRepository) {
    suspend operator fun invoke(ITAssetModel: ITAssetModel){
        repository.updateITAsset(ITAssetModel)
    }
}