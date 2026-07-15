package com.jnarvaez.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnarvaez.appsemana03.core.utils.EstadoEquipo
import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.domain.usecase.EquipoUseCases
import com.jnarvaez.appsemana03.presentation.event.EventBus
import com.jnarvaez.appsemana03.presentation.event.UiEvent
import com.jnarvaez.appsemana03.presentation.screens.editar.EditarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditarViewModel(private val useCases: EquipoUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(EditarUiState())
    val uiState = _uiState.asStateFlow()

    fun buscarEquipo(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val equipo = useCases.getEquipo(id)
                if (equipo.estado == EstadoEquipo.REPARACION) {
                    _uiState.value = _uiState.value.copy(isLoading = false, equipoNoDisponible = true)
                    EventBus.send(UiEvent.Warning("No se puede editar un equipo en reparación"))
                    return@launch
                }
                _uiState.value = _uiState.value.copy(
                    id = equipo.id,
                    equipo = equipo.equipo,
                    usuarioAsignado = equipo.usuarioAsignado,
                    usuarioAsignadoOriginal = equipo.usuarioAsignado,
                    fechaEntrega = equipo.fechaEntrega,
                    obs = equipo.obs,
                    estado = equipo.estado,
                    motivoReparacion = equipo.motivoReparacion,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
                EventBus.send(UiEvent.Error(e.message ?: "Error al cargar equipo"))
            }
        }
    }

    fun onEquipoChange(valor: String) {
        _uiState.value = _uiState.value.copy(equipo = valor)
    }

    fun onUsuarioAsignadoChange(valor: String) {
        _uiState.value = _uiState.value.copy(usuarioAsignado = valor)
    }

    fun onFechaEntregaChange(valor: String) {
        _uiState.value = _uiState.value.copy(fechaEntrega = valor)
    }

    fun onObsChange(valor: String) {
        _uiState.value = _uiState.value.copy(obs = valor)
    }

    fun actualizarEquipo() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val huboReasignacion = _uiState.value.usuarioAsignado.trim() !=
                    _uiState.value.usuarioAsignadoOriginal.trim()
                val equipoNombre = _uiState.value.equipo
                val usuarioAnterior = _uiState.value.usuarioAsignadoOriginal
                val usuarioNuevo = _uiState.value.usuarioAsignado

                val equipo = Equipo(
                    id = uiState.value.id,
                    equipo = _uiState.value.equipo,
                    usuarioAsignado = _uiState.value.usuarioAsignado,
                    fechaEntrega = _uiState.value.fechaEntrega,
                    obs = _uiState.value.obs,
                    estado = _uiState.value.estado,
                    motivoReparacion = _uiState.value.motivoReparacion
                )
                useCases.actualizarEquipo(equipo)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    huboReasignacion = huboReasignacion,
                    equipoReasignado = equipoNombre,
                    usuarioAnterior = usuarioAnterior,
                    usuarioNuevo = usuarioNuevo
                )
                EventBus.send(UiEvent.Success("Equipo actualizado correctamente"))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(
            isSuccess = false,
            huboReasignacion = false,
            equipoReasignado = "",
            usuarioAnterior = "",
            usuarioNuevo = ""
        )
    }

    private suspend fun validarFormulario(): Boolean {
        when {
            _uiState.value.equipo.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el equipo"))
                return false
            }
            _uiState.value.usuarioAsignado.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el usuario asignado"))
                return false
            }
            _uiState.value.fechaEntrega.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese la fecha de entrega"))
                return false
            }
            _uiState.value.obs.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese las observaciones"))
                return false
            }
        }
        return true
    }
}
