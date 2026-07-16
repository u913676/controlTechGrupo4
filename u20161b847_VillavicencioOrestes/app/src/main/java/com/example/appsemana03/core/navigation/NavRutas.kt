package com.example.appsemana03.core.navigation

object NavRutas {
    const val INICIO  = "inicio"
    const val AGREGAR  = "agregar"
    const val EDITAR = "editar"
    const val LISTAR  = "listar"
    const val BUSCAR  = "buscar"
    const val ASIGNAR  = "asignar"
    const val ASIGNAR_FORM  = "asignar_form"
    const val HISTORIAL  = "historial"
    const val HISTORIAL_DETALLE  = "historial_detalle"

    fun getTitulo(ruta:String?):String{
        return when{
            ruta == INICIO -> "ControlTech"
            ruta == AGREGAR -> "Nuevo Libro"
            ruta?.startsWith(EDITAR) ==  true -> "Editar Libro"
            ruta?.startsWith(BUSCAR) ==  true -> "Buscar Libro"
            ruta == ASIGNAR -> "Equipos para asignar"
            ruta == ASIGNAR_FORM -> "Asignar equipo"
            ruta == HISTORIAL -> "Historial de Equipos"
            ruta == HISTORIAL_DETALLE -> "Historial del Equipo"
            else -> "ControlTech"
        }
    }
}