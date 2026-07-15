package com.martinez.appitasset.domain.usecase


import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.repository.ITAssetRepository

class GetITAssetUseCase(private val repository: ITAssetRepository) {
    suspend operator fun invoke(id: String): ITAssetModel{
        return repository.getITAssetById(id)
    }
}