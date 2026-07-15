package com.jnarvaez.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jnarvaez.appsemana03.domain.usecase.EquipoUseCases
import com.jnarvaez.appsemana03.presentation.screens.historial.EventoHistorial
import com.jnarvaez.appsemana03.presentation.screens.historial.HistorialUiState
import com.jnarvaez.appsemana03.presentation.screens.historial.TipoEventoHistorial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistorialViewModel(private val useCases: EquipoUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(HistorialUiState())
    val uiState = _uiState.asStateFlow()

    fun agregarReasignacion(equipo: String, usuarioAnterior: String, usuarioNuevo: String) {
        agregarEvento(
            EventoHistorial(
                tipo = TipoEventoHistorial.REASIGNACION,
                equipo = equipo,
                fecha = fechaActual(),
                usuarioAnterior = usuarioAnterior,
                usuarioNuevo = usuarioNuevo
            )
        )
    }

    fun agregarReparacion(equipo: String, motivo: String) {
        agregarEvento(
            EventoHistorial(
                tipo = TipoEventoHistorial.REPARACION,
                equipo = equipo,
                fecha = fechaActual(),
                motivo = motivo
            )
        )
    }

    private fun agregarEvento(evento: EventoHistorial) {
        _uiState.value = _uiState.value.copy(
            eventos = listOf(evento) + _uiState.value.eventos
        )
    }

    private fun fechaActual(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}
