package com.martinez.appitasset.data.remote.datasource

import com.martinez.appitasset.core.network.ApiService
import com.martinez.appitasset.data.remote.dto.ApiResponse
import com.martinez.appitasset.data.remote.dto.ITAssetDto
import com.martinez.appitasset.data.remote.dto.addITAssetRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun getITAssets(): ApiResponse?{
        return api.getITAssets().body()
    }

    suspend fun addITAsset(request: addITAssetRequest): ITAssetDto?{
        return api.addITAsset(request).body()
    }

    suspend fun getITAssetById(id: String): ITAssetDto?{
        return api.getITAssetById(id).body()?.data
    }

    suspend fun updateITAsset(id:String, request: addITAssetRequest): ITAssetDto?{
        return api.updateITAsset(id,request).body()
    }

    suspend fun deleteITAsset(id: String){
        api.deleteITAsset(id)
    }
}