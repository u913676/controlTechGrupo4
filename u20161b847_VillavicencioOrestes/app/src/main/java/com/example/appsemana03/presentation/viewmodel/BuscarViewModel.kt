package com.example.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsemana03.presentation.event.EventBus
import com.example.appsemana03.presentation.event.UiEvent
import com.example.appsemana03.presentation.screens.buscar.BuscarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BuscarViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(value = BuscarUiState())
    val uiState = _uiState.asStateFlow()

    fun onNombresChange(valor: String) {
        _uiState.value = uiState.value.copy(nombres = valor)
    }

    fun onDniChange(valor: String) {
        _uiState.value = uiState.value.copy(dni = valor)
    }

    fun onEdadChange(valor: String) {
        _uiState.value = uiState.value.copy(edad = valor)
    }

    fun onCicloChange(valor: String) {
        _uiState.value = uiState.value.copy(ciclo = valor)
    }

    fun onFacultadChange(valor: String) {
        _uiState.value = uiState.value.copy(facultad = valor)
    }

    fun onCarreraChange(valor: String) {
        _uiState.value = uiState.value.copy(carrera = valor)
    }

    fun onDireccionChange(valor: String) {
        _uiState.value = uiState.value.copy(direccion = valor)
    }

    fun onCorreoChange(valor: String) {
        _uiState.value = uiState.value.copy(correo = valor)
    }

    fun onLinkedinChange(valor: String) {
        _uiState.value = uiState.value.copy(linkedin = valor)
    }

    fun guardarAlumno() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }
            EventBus.send(UiEvent.Success("Alumno guardado correctamente"))
        }
    }

    private suspend fun validarFormulario(): Boolean {
        when {
            _uiState.value.nombres.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese los nombres y apellidos"))
                return false
            }
            _uiState.value.dni.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el DNI"))
                return false
            }
            _uiState.value.edad.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese la edad"))
                return false
            }
            _uiState.value.ciclo.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el ciclo"))
                return false
            }
            _uiState.value.facultad.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese la facultad"))
                return false
            }
            _uiState.value.carrera.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese la carrera"))
                return false
            }
            _uiState.value.direccion.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese la dirección"))
                return false
            }
            _uiState.value.correo.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el correo"))
                return false
            }
            _uiState.value.linkedin.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese el LinkedIn"))
                return false
            }
        }
        return true
    }
}
