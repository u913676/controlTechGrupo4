package com.jnarvaez.appsemana03.data.mapper

import com.jnarvaez.appsemana03.core.utils.EstadoEquipo
import com.jnarvaez.appsemana03.data.remote.dto.EquipoDto
import com.jnarvaez.appsemana03.data.remote.dto.InsertarEquipoRequest
import com.jnarvaez.appsemana03.domain.model.Equipo

object EquipoMapper {
    fun toDomain(dto: EquipoDto): Equipo =
        Equipo(
            id = dto.id,
            equipo = dto.equipo,
            usuarioAsignado = dto.usuarioAsignado,
            fechaEntrega = dto.fechaEntrega,
            obs = dto.obs,
            estado = dto.estado ?: EstadoEquipo.ACTIVO,
            motivoReparacion = dto.motivoReparacion
        )

    fun toInsertRequest(equipo: Equipo): InsertarEquipoRequest =
        InsertarEquipoRequest(
            equipo = equipo.equipo,
            usuarioAsignado = equipo.usuarioAsignado,
            fechaEntrega = equipo.fechaEntrega,
            obs = equipo.obs,
            estado = equipo.estado,
            motivoReparacion = equipo.motivoReparacion
        )
}
