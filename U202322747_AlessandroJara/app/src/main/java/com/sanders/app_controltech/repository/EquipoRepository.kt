package com.sanders.app_controltech.repository

import com.sanders.app_controltech.model.Equipo
import com.sanders.app_controltech.network.ApiService
import com.sanders.app_controltech.network.RetrofitClient

class EquipoRepository(
    private val apiService: ApiService = RetrofitClient.apiService
) {

    suspend fun obtenerEquipos(): Result<List<Equipo>> {
        return try {
            val response = apiService.obtenerEquipos()

            if (response.success) {
                Result.success(response.data.items)
            } else {
                Result.failure(
                    Exception("La API no pudo cargar los equipos")
                )
            }
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}