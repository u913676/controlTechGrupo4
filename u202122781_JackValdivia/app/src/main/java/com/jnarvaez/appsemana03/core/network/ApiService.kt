package com.jnarvaez.appsemana03.core.network

import com.jnarvaez.appsemana03.data.remote.dto.EquipoDetalleResponse
import com.jnarvaez.appsemana03.data.remote.dto.EquipoDto
import com.jnarvaez.appsemana03.data.remote.dto.EquiposResponse
import com.jnarvaez.appsemana03.data.remote.dto.InsertarEquipoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("equipos")
    suspend fun getEquipos(): Response<EquiposResponse>

    @POST("equipos")
    suspend fun insertarEquipo(@Body request: InsertarEquipoRequest): Response<EquipoDto>

    @GET("equipos/{id}")
    suspend fun getEquipoById(@Path("id") id: String): Response<EquipoDetalleResponse>

    @PUT("equipos/{id}")
    suspend fun actualizarEquipo(@Path("id") id: String, @Body request: InsertarEquipoRequest): Response<EquipoDto>

    @DELETE("equipos/{id}")
    suspend fun eliminarEquipo(@Path("id") id: String): Response<Unit>
}
