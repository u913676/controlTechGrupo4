package com.sanders.app_controltech.network

import com.sanders.app_controltech.model.EquiposResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("equipos")
    suspend fun obtenerEquipos(
        @Query("limit") limit: Int = 100
    ): EquiposResponse
}