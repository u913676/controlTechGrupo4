package com.jnarvaez.appsemana03.presentation.screens.equipos

import com.jnarvaez.appsemana03.domain.model.Equipo

data class EquiposUiState(
    val listaEquipos: List<Equipo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isDelete: Boolean = false
)
