package com.sanders.app_controltech.ui.equipos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanders.app_controltech.repository.EquipoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class EquiposViewModel(
    private val repository: EquipoRepository = EquipoRepository()
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(EquiposUiState())

    val uiState: StateFlow<EquiposUiState> =
        _uiState.asStateFlow()

    init {
        cargarEquipos()
    }

    fun cargarEquipos() {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    cargando = true,
                    mensajeError = null
                )
            }

            repository.obtenerEquipos()
                .onSuccess { equipos ->
                    _uiState.update {
                        it.copy(
                            equipos = equipos,
                            equiposFiltrados = equipos,
                            cargando = false,
                            mensajeError = null
                        )
                    }

                    aplicarFiltros()
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            cargando = false,
                            mensajeError =
                                error.message
                                    ?: "No se pudieron cargar los equipos"
                        )
                    }
                }
        }
    }

    fun onTextoBusquedaChange(
        nuevoTexto: String
    ) {
        _uiState.update {
            it.copy(textoBusqueda = nuevoTexto)
        }

        aplicarFiltros()
    }

    fun onCategoriaChange(
        nuevaCategoria: String
    ) {
        _uiState.update {
            it.copy(
                categoriaSeleccionada =
                    nuevaCategoria
            )
        }

        aplicarFiltros()
    }

    fun limpiarFiltros() {
        _uiState.update {
            it.copy(
                textoBusqueda = "",
                categoriaSeleccionada = "Todos",
                equiposFiltrados = it.equipos
            )
        }
    }

    private fun aplicarFiltros() {
        val estadoActual = _uiState.value

        val texto = estadoActual.textoBusqueda
            .trim()
            .lowercase()

        val categoria =
            estadoActual.categoriaSeleccionada

        val resultado =
            estadoActual.equipos.filter { equipo ->

                val coincideTexto =
                    texto.isBlank() ||
                            equipo.nombre
                                .lowercase()
                                .contains(texto) ||
                            equipo.marca
                                .lowercase()
                                .contains(texto) ||
                            equipo.modelo
                                .lowercase()
                                .contains(texto) ||
                            equipo.codigo
                                .lowercase()
                                .contains(texto)

                val coincideCategoria =
                    categoria == "Todos" ||
                            equipo.categoria.equals(
                                categoria,
                                ignoreCase = true
                            )

                coincideTexto &&
                        coincideCategoria
            }

        _uiState.update {
            it.copy(
                equiposFiltrados = resultado
            )
        }
    }
}