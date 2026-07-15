package com.sanders.app_controltech.ui.equipos

import com.sanders.app_controltech.model.Equipo

data class EquiposUiState(
    val equipos: List<Equipo> = emptyList(),
    val equiposFiltrados: List<Equipo> = emptyList(),
    val textoBusqueda: String = "",
    val categoriaSeleccionada: String = "Todos",

    val categorias: List<String> = listOf(
        "Todos",
        "Laptop",
        "PC",
        "Monitor",
        "Impresora",
        "Redes"
    ),

    val cargando: Boolean = false,
    val mensajeError: String? = null
)