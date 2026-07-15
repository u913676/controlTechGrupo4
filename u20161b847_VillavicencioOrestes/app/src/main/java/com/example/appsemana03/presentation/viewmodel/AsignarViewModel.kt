package com.example.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsemana03.presentation.event.EventBus
import com.example.appsemana03.presentation.event.UiEvent
import com.example.appsemana03.presentation.screens.asignar.AsignarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AsignarViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(value = AsignarUiState())
    val uiState = _uiState.asStateFlow()

    fun onDestinatarioChange(valor: String){
        _uiState.value = uiState.value.copy(destinatario = valor)
    }

    fun onFechaEntregaChange(valor: String){
        _uiState.value = uiState.value.copy(fechaEntrega = valor)
    }

    fun onDetallesChange(valor: String){
        _uiState.value = uiState.value.copy(detalles = valor)
    }

    fun confirmarAsignacion(){
        viewModelScope.launch {
            if(!validarFormulario()){
                return@launch
            }
            _uiState.value = uiState.value.copy(estadoEquipo = "Asignado")
            EventBus.send(UiEvent.Success("Equipo asignado. Se notificó por correo a ${_uiState.value.destinatario}"))
        }
    }

    private suspend fun validarFormulario(): Boolean{
        when{
            _uiState.value.estadoEquipo != "Disponible" -> {
                EventBus.send(UiEvent.Error("Solo se pueden asignar equipos con estado Disponible"))
                return false
            }
            _uiState.value.destinatario.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el usuario o área de destino"))
                return false
            }
            _uiState.value.fechaEntrega.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese la fecha de entrega"))
                return false
            }
        }
        return true
    }
}
