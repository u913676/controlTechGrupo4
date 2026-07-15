package com.martinez.appitasset.domain.usecase


import com.martinez.appitasset.domain.repository.ITAssetRepository

class DeleteITAssetUseCase(private val repository: ITAssetRepository) {
    suspend operator fun invoke(id: String){
        repository.deleteITAsset(id)
    }
}