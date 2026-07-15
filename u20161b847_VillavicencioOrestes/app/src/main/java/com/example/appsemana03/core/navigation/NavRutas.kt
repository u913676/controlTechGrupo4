package com.example.appsemana03.core.navigation

object NavRutas {
    const val INICIO  = "inicio"
    const val AGREGAR  = "agregar"
    const val EDITAR = "editar"
    const val LISTAR  = "listar"
    const val BUSCAR  = "buscar"
    const val ASIGNAR  = "asignar"
    const val HISTORIAL  = "historial"

    fun getTitulo(ruta:String?):String{
        return when{
            ruta == INICIO -> "ControlTech"
            ruta == AGREGAR -> "Nuevo Libro"
            ruta?.startsWith(EDITAR) ==  true -> "Editar Libro"
            ruta?.startsWith(BUSCAR) ==  true -> "Buscar Libro"
            ruta == ASIGNAR -> "Asignar equipo"
            ruta == HISTORIAL -> "Historial del Equipo"
            else -> "ControlTech"
        }
    }
}