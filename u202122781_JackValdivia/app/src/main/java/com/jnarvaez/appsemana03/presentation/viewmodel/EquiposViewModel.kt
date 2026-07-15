package com.jnarvaez.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.domain.usecase.EquipoUseCases
import com.jnarvaez.appsemana03.presentation.event.EventBus
import com.jnarvaez.appsemana03.presentation.event.UiEvent
import com.jnarvaez.appsemana03.presentation.screens.equipos.EquiposUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquiposViewModel(private val useCases: EquipoUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(EquiposUiState())
    val uiState = _uiState.asStateFlow()
    private var equipos = listOf<Equipo>()

    init {
        cargarEquipos()
    }

    fun cargarEquipos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                equipos = useCases.getEquipos()
                _uiState.value = _uiState.value.copy(
                    listaEquipos = equipos,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
                EventBus.send(UiEvent.Error("Error al listar equipos: ${e.message}"))
            }
        }
    }

    fun setEliminar(estado: Boolean) {
        _uiState.value = _uiState.value.copy(isDelete = estado)
    }

    fun eliminarEquipo(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                useCases.eliminarEquipo(id)
                EventBus.send(UiEvent.Success("Equipo eliminado"))
                _uiState.value = _uiState.value.copy(isLoading = false)
                cargarEquipos()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
                EventBus.send(UiEvent.Error("Error al eliminar equipo: ${e.message}"))
            }
        }
    }
}
