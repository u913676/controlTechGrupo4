package com.martinez.appitasset.core.network

import com.martinez.appitasset.data.remote.dto.ApiResponse
import com.martinez.appitasset.data.remote.dto.addITAssetRequest
import com.martinez.appitasset.data.remote.dto.ITAssetDto
import com.martinez.appitasset.data.remote.dto.ITAssetDetailResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("itassets")
    suspend fun getITAssets(): Response<ApiResponse>

    @POST("itassets")
    suspend fun addITAsset(@Body request: addITAssetRequest): Response<ITAssetDto>

    @GET("itassets/{id}")
    suspend fun getITAssetById(@Path("id") id: String):
            Response<ITAssetDetailResponse>

    @PUT("itassets/{id}")
    suspend fun updateITAsset(@Path("id") id: String,
                              @Body request: addITAssetRequest): Response<ITAssetDto>

    @DELETE("itassets/{id}")
    suspend fun deleteITAsset(@Path("id") id: String): Response<Unit>
}