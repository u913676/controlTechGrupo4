package com.jnarvaez.appsemana03.presentation.screens.historial

enum class TipoEventoHistorial {
    REASIGNACION,
    REPARACION
}

data class EventoHistorial(
    val tipo: TipoEventoHistorial,
    val equipo: String,
    val fecha: String,
    val usuarioAnterior: String? = null,
    val usuarioNuevo: String? = null,
    val motivo: String? = null
)
