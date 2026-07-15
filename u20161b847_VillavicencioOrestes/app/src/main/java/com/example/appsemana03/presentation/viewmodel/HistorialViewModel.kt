package com.example.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appsemana03.presentation.screens.historial.EventoHistorial
import com.example.appsemana03.presentation.screens.historial.HistorialUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistorialViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(value = HistorialUiState())
    val uiState = _uiState.asStateFlow()

    init {
        cargarHistorial()
    }

    private fun cargarHistorial(){
        val lista = listOf(
            EventoHistorial(
                tipo = "Devuelto",
                fecha = "10 Jul 2026",
                fechaOrden = "2026-07-10",
                responsable = "Admin. Carlos Ruiz",
                descripcion = "El equipo ha sido reintegrado al inventario central tras la finalización del proyecto de desarrollo externo. Inspección técnica aprobada sin observaciones."
            ),
            EventoHistorial(
                tipo = "Reparación",
                fecha = "25 Jun 2026",
                fechaOrden = "2026-06-25",
                responsable = "Soporte: Ing. Mario Salas",
                descripcion = "Sustitución de ventilador del procesador por ruido excesivo. Se realizó mantenimiento preventivo y limpieza de componentes internos."
            ),
            EventoHistorial(
                tipo = "Cambio de ubicación",
                fecha = "18 May 2026",
                fechaOrden = "2026-05-18",
                responsable = "Usuario: Elena Moreno",
                descripcion = "Traslado programado para equipar el nuevo laboratorio de pruebas de rendimiento. Responsable del traslado: Logística interna."
            ),
            EventoHistorial(
                tipo = "Asignado",
                fecha = "02 Abr 2026",
                fechaOrden = "2026-04-02",
                responsable = "Usuario: Elena Moreno",
                descripcion = "Equipo asignado formalmente para el rol de Senior Software Engineer. Periodo de garantía vigente."
            ),
            EventoHistorial(
                tipo = "Registrado",
                fecha = "20 Feb 2026",
                fechaOrden = "2026-02-20",
                responsable = "Ingreso inicial al sistema",
                descripcion = "Equipo dado de alta en el inventario. Configuración inicial de BIOS y SO completada."
            )
        )
        // Criterio 3: orden cronológico descendente (más reciente primero)
        _uiState.value = uiState.value.copy(
            eventos = lista.sortedByDescending { it.fechaOrden }
        )
    }
}
