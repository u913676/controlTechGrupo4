package com.jnarvaez.appsemana03.data.repository

import com.jnarvaez.appsemana03.data.mapper.EquipoMapper
import com.jnarvaez.appsemana03.data.remote.datasource.RemoteDataSource
import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.domain.repository.EquipoRepository

class EquipoRepositoryImpl(private val remoteDataSource: RemoteDataSource) : EquipoRepository {
    override suspend fun getEquipos(): List<Equipo> {
        return remoteDataSource.getEquipos()?.data?.items?.map {
            EquipoMapper.toDomain(it)
        } ?: emptyList()
    }

    override suspend fun insertarEquipo(equipo: Equipo) {
        remoteDataSource.insertarEquipo(EquipoMapper.toInsertRequest(equipo))
    }

    override suspend fun getEquipoById(id: String): Equipo {
        return remoteDataSource.getEquipoById(id)?.let { EquipoMapper.toDomain(it) }
            ?: throw Exception("Equipo no encontrado")
    }

    override suspend fun actualizarEquipo(equipo: Equipo) {
        remoteDataSource.actualizarEquipo(equipo.id, EquipoMapper.toInsertRequest(equipo))
    }

    override suspend fun eliminarEquipo(id: String) {
        remoteDataSource.eliminarEquipo(id)
    }
}
