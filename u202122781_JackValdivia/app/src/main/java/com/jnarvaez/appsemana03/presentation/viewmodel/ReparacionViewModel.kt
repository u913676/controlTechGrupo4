package com.jnarvaez.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnarvaez.appsemana03.core.utils.EstadoEquipo
import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.domain.usecase.EquipoUseCases
import com.jnarvaez.appsemana03.presentation.event.EventBus
import com.jnarvaez.appsemana03.presentation.event.UiEvent
import com.jnarvaez.appsemana03.presentation.screens.reparacion.ReparacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReparacionViewModel(private val useCases: EquipoUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(ReparacionUiState())
    val uiState = _uiState.asStateFlow()

    fun buscarEquipo(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val equipo = useCases.getEquipo(id)
                if (equipo.estado == EstadoEquipo.REPARACION) {
                    _uiState.value = _uiState.value.copy(isLoading = false, equipoNoDisponible = true)
                    EventBus.send(UiEvent.Warning("Este equipo ya está en reparación"))
                    return@launch
                }
                _uiState.value = _uiState.value.copy(
                    id = equipo.id,
                    equipo = equipo.equipo,
                    usuarioAsignado = equipo.usuarioAsignado,
                    fechaEntrega = equipo.fechaEntrega,
                    obs = equipo.obs,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
                EventBus.send(UiEvent.Error(e.message ?: "Error al cargar equipo"))
            }
        }
    }

    fun onMotivoChange(valor: String) {
        _uiState.value = _uiState.value.copy(motivo = valor)
    }

    fun enviarAReparacion() {
        viewModelScope.launch {
            if (_uiState.value.motivo.isBlank()) {
                EventBus.send(UiEvent.Warning("Ingrese el motivo de reparación"))
                return@launch
            }
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val motivo = _uiState.value.motivo.trim()
                val equipoNombre = _uiState.value.equipo

                val equipo = Equipo(
                    id = _uiState.value.id,
                    equipo = _uiState.value.equipo,
                    usuarioAsignado = _uiState.value.usuarioAsignado,
                    fechaEntrega = _uiState.value.fechaEntrega,
                    obs = _uiState.value.obs,
                    estado = EstadoEquipo.REPARACION,
                    motivoReparacion = motivo
                )
                useCases.actualizarEquipo(equipo)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    equipoEnviado = equipoNombre,
                    motivoEnviado = motivo
                )
                EventBus.send(UiEvent.Success("Equipo enviado a reparación"))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
                EventBus.send(UiEvent.Error("Error al enviar a reparación: ${e.message}"))
            }
        }
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(
            isSuccess = false,
            equipoEnviado = "",
            motivoEnviado = "",
            motivo = ""
        )
    }
}
