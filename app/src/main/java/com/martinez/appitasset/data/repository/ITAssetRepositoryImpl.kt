package com.martinez.appitasset.data.repository

import com.martinez.appitasset.data.mapper.ITAssetMapper
import com.martinez.appitasset.data.remote.datasource.RemoteDataSource
import com.martinez.appitasset.domain.model.ITAssetModel
import com.martinez.appitasset.domain.repository.ITAssetRepository

class ITAssetRepositoryImpl(
    private val remoteDataSource: RemoteDataSource): ITAssetRepository {

    override suspend fun getITAssets(): List<ITAssetModel> {
        return remoteDataSource.getITAssets()?.data?.items?.map {
            ITAssetMapper.toDomain(it)
        }?:emptyList()
    }

    override suspend fun addITAsset(model: ITAssetModel) {
        remoteDataSource.addITAsset(
            ITAssetMapper.toInsertITAssetRequest(model))
    }

    override suspend fun getITAssetById(id: String): ITAssetModel {
        return remoteDataSource.getITAssetById(id)?.let{
            ITAssetMapper.toDomain(it) } ?:
        throw Exception("ITAsset not found")
    }

    override suspend fun updateITAsset(model: ITAssetModel) {
        remoteDataSource.updateITAsset(
            model.id, ITAssetMapper.toInsertITAssetRequest(
                model))
    }

    override suspend fun deleteITAsset(id: String) {
        remoteDataSource.deleteITAsset(id)
    }
}