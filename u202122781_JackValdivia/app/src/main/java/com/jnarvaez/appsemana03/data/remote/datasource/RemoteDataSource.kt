package com.jnarvaez.appsemana03.data.remote.datasource

import com.jnarvaez.appsemana03.core.network.ApiService
import com.jnarvaez.appsemana03.data.remote.dto.EquiposResponse
import com.jnarvaez.appsemana03.data.remote.dto.EquipoDto
import com.jnarvaez.appsemana03.data.remote.dto.InsertarEquipoRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun getEquipos(): EquiposResponse? {
        return api.getEquipos().body()
    }

    suspend fun insertarEquipo(request: InsertarEquipoRequest): EquipoDto? {
        return api.insertarEquipo(request).body()
    }

    suspend fun getEquipoById(id: String): EquipoDto? {
        return api.getEquipoById(id).body()?.data
    }

    suspend fun actualizarEquipo(id: String, request: InsertarEquipoRequest): EquipoDto? {
        return api.actualizarEquipo(id, request).body()
    }

    suspend fun eliminarEquipo(id: String) {
        api.eliminarEquipo(id)
    }
}
