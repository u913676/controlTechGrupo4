package com.example.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsemana03.presentation.event.EventBus
import com.example.appsemana03.presentation.event.UiEvent
import com.example.appsemana03.presentation.model.EquipoResumen
import com.example.appsemana03.presentation.model.TipoEquipo
import com.example.appsemana03.presentation.screens.asignar.AsignarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private fun fechaHoy(): String =
    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

class AsignarViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(value = AsignarUiState(fechaEntrega = fechaHoy()))
    val uiState = _uiState.asStateFlow()

    init {
        cargarEquipos()
    }

    // Lista de equipos que se muestra en la pantalla de Asignar.
    private fun cargarEquipos() {
        val equipos = listOf(
            EquipoResumen("CT-LP-4022", "Laptop Dell Latitude", "Dell / 5420 i7 11th Gen", "Disponible", "Piso 4 - IT Lab", TipoEquipo.LAPTOP),
            EquipoResumen("CT-PC-1088", "Workstation HP Z2", "HP / Tower G5 Core i9", "Asignado", "Carlos Méndez - Diseño", TipoEquipo.WORKSTATION),
            EquipoResumen("CT-PR-0041", "Impresora Laser Jet", "Brother / HL-L2350DW", "Reparación", "Soporte Técnico Ext.", TipoEquipo.IMPRESORA),
            EquipoResumen("CT-WS-2050", "Workstation Dell Precision", "Dell / Precision 5680", "Devuelto", "Admin. Carlos Ruiz", TipoEquipo.WORKSTATION),
            EquipoResumen("CT-LP-3390", "Laptop Lenovo ThinkPad", "Lenovo / T14 Ryzen 7", "Disponible", "Almacén Central", TipoEquipo.LAPTOP)
        )
        _uiState.value = uiState.value.copy(equipos = equipos)
    }

    // Solo permite pasar al formulario si el equipo está "Disponible".
    fun intentarAsignar(equipo: EquipoResumen, onPermitido: () -> Unit) {
        if (equipo.estado == "Disponible") {
            seleccionarEquipo(equipo)
            onPermitido()
        } else {
            viewModelScope.launch {
                EventBus.send(UiEvent.Warning("Solo se pueden asignar equipos disponibles"))
            }
        }
    }

    // Precarga los datos del equipo elegido en el formulario de asignación.
    private fun seleccionarEquipo(equipo: EquipoResumen) {
        _uiState.value = uiState.value.copy(
            activoId = equipo.id,
            nombreEquipo = equipo.nombre,
            especificaciones = equipo.modelo,
            estadoEquipo = equipo.estado,
            destinatario = "",
            detalles = "",
            fechaEntrega = fechaHoy()
        )
    }

    fun onDestinatarioChange(valor: String){
        _uiState.value = uiState.value.copy(destinatario = valor)
    }

    fun onFechaEntregaChange(valor: String){
        _uiState.value = uiState.value.copy(fechaEntrega = valor)
    }

    fun onDetallesChange(valor: String){
        _uiState.value = uiState.value.copy(detalles = valor)
    }

    fun confirmarAsignacion(onExito: () -> Unit){
        viewModelScope.launch {
            if(!validarFormulario()){
                return@launch
            }
            val destinatario = _uiState.value.destinatario
            // Criterio 3: el estado cambia automáticamente a "Asignado" (se refleja en la card)
            // y luego se limpia el formulario.
            _uiState.value = uiState.value.copy(
                estadoEquipo = "Asignado",
                destinatario = "",
                fechaEntrega = fechaHoy(),
                detalles = ""
            )
            EventBus.send(UiEvent.Success("Equipo asignado. Se notificó por correo a $destinatario"))
            // Tras el mensaje de confirmación, se regresa a la lista de equipos.
            onExito()
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
