package com.martinez.appitasset.domain.repository

import com.martinez.appitasset.domain.model.ITAssetModel

interface ITAssetRepository {
    suspend fun getITAssets(): List<ITAssetModel>
    suspend fun addITAsset(model: ITAssetModel)
    suspend fun getITAssetById(id: String): ITAssetModel
    suspend fun updateITAsset(model: ITAssetModel)
    suspend fun deleteITAsset(id: String)

}