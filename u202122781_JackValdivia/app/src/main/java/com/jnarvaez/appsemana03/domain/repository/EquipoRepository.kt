package com.jnarvaez.appsemana03.domain.repository

import com.jnarvaez.appsemana03.domain.model.Equipo

interface EquipoRepository {
    suspend fun getEquipos(): List<Equipo>
    suspend fun insertarEquipo(equipo: Equipo)
    suspend fun getEquipoById(id: String): Equipo
    suspend fun actualizarEquipo(equipo: Equipo)
    suspend fun eliminarEquipo(id: String)
}
