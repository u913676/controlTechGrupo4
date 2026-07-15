package com.martinez.appitasset.domain.usecase


import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.repository.ITAssetRepository

class AddITAssetUseCase(private val repository: ITAssetRepository) {
    suspend operator fun invoke(model: ITAssetModel){
        repository.addITAsset(model)
    }
}