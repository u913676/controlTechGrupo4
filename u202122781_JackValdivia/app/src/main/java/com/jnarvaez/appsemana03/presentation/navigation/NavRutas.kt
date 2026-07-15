package com.jnarvaez.appsemana03.presentation.navigation

object NavRutas {
    const val EQUIPOS = "equipos"
    const val HISTORIAL = "historial"
    const val EDITAR = "editar"
    const val REPARACION = "reparacion"

    fun getTitulo(ruta: String?): String {
        return when {
            ruta == EQUIPOS -> "APP Equipos"
            ruta == HISTORIAL -> "Historial"
            ruta?.startsWith(EDITAR) == true -> "Editar Equipo"
            ruta?.startsWith(REPARACION) == true -> "Enviar a reparación"
            else -> "APP Equipos"
        }
    }
}
