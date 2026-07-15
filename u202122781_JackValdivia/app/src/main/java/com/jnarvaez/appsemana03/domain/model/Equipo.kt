package com.jnarvaez.appsemana03.domain.model

import com.jnarvaez.appsemana03.core.utils.EstadoEquipo

data class Equipo(
    val id: String,
    val equipo: String,
    val usuarioAsignado: String,
    val fechaEntrega: String,
    val obs: String,
    val estado: String = EstadoEquipo.ACTIVO,
    val motivoReparacion: String? = null
)
