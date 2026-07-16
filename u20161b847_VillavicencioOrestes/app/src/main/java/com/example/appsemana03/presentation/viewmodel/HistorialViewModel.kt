package com.example.appsemana03.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appsemana03.presentation.model.EquipoResumen
import com.example.appsemana03.presentation.model.TipoEquipo
import com.example.appsemana03.presentation.screens.historial.EventoHistorial
import com.example.appsemana03.presentation.screens.historial.HistorialUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistorialViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(value = HistorialUiState())
    val uiState = _uiState.asStateFlow()

    init {
        cargarEquipos()
    }

    // Lista de equipos que se muestra en la pantalla de Historial.
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

    // Al tocar una card, se carga su cabecera y su propio historial de eventos.
    fun seleccionarEquipo(equipo: EquipoResumen) {
        _uiState.value = uiState.value.copy(
            activoId = equipo.id,
            nombreEquipo = equipo.nombre,
            estadoEquipo = equipo.estado,
            // Orden cronológico descendente (más reciente primero).
            eventos = eventosDe(equipo.id).sortedByDescending { it.fechaOrden }
        )
    }

    // Historial de movimientos de cada equipo. El evento más reciente coincide con su estado actual.
    private fun eventosDe(equipoId: String): List<EventoHistorial> {
        return when (equipoId) {
            "CT-LP-4022" -> listOf(
                EventoHistorial("Disponible", "06 Jul 2026", "2026-07-06", "Admin. Carlos Ruiz", "Equipo verificado y marcado como DISPONIBLE en inventario. Ubicado en Piso 4 - IT Lab, listo para una nueva asignación."),
                EventoHistorial("Devuelto", "04 Jul 2026", "2026-07-04", "Admin. Carlos Ruiz", "Equipo devuelto y reintegrado al inventario tras finalizar su uso en el área de soporte."),
                EventoHistorial("Asignado", "10 Feb 2026", "2026-02-10", "Usuario: Ana Torres", "Asignada temporalmente al área de soporte para tareas de campo."),
                EventoHistorial("Registrado", "15 Ene 2026", "2026-01-15", "Ingreso inicial al sistema", "Equipo dado de alta en el inventario. Configuración inicial completada.")
            )
            "CT-PC-1088" -> listOf(
                EventoHistorial("Asignado", "08 Jul 2026", "2026-07-08", "Usuario: Carlos Méndez", "Equipo asignado formalmente al área de Diseño. Actualmente en uso."),
                EventoHistorial("Cambio de ubicación", "20 May 2026", "2026-05-20", "Logística interna", "Trasladado al piso del área de Diseño para su próxima asignación."),
                EventoHistorial("Registrado", "10 Feb 2026", "2026-02-10", "Ingreso inicial al sistema", "Equipo dado de alta en el inventario. Configuración inicial completada.")
            )
            "CT-PR-0041" -> listOf(
                EventoHistorial("Reparación", "09 Jul 2026", "2026-07-09", "Soporte: Ing. Mario Salas", "Ingresa a reparación con soporte técnico externo por atascos frecuentes. Cambio de tambor y rodillo."),
                EventoHistorial("Asignado", "01 Mar 2026", "2026-03-01", "Usuario: Recepción", "Asignada al área de recepción para impresión de documentos."),
                EventoHistorial("Registrado", "12 Ene 2026", "2026-01-12", "Ingreso inicial al sistema", "Equipo dado de alta en el inventario.")
            )
            "CT-WS-2050" -> listOf(
                EventoHistorial("Devuelto", "10 Jul 2026", "2026-07-10", "Admin. Carlos Ruiz", "El equipo ha sido reintegrado al inventario central tras la finalización del proyecto de desarrollo externo. Inspección técnica aprobada sin observaciones."),
                EventoHistorial("Reparación", "25 Jun 2026", "2026-06-25", "Soporte: Ing. Mario Salas", "Sustitución de ventilador del procesador por ruido excesivo. Se realizó mantenimiento preventivo y limpieza de componentes internos."),
                EventoHistorial("Cambio de ubicación", "18 May 2026", "2026-05-18", "Usuario: Elena Moreno", "Traslado programado para equipar el nuevo laboratorio de pruebas de rendimiento. Responsable del traslado: Logística interna."),
                EventoHistorial("Asignado", "02 Abr 2026", "2026-04-02", "Usuario: Elena Moreno", "Equipo asignado formalmente para el rol de Senior Software Engineer. Periodo de garantía vigente."),
                EventoHistorial("Registrado", "20 Feb 2026", "2026-02-20", "Ingreso inicial al sistema", "Equipo dado de alta en el inventario. Configuración inicial de BIOS y SO completada.")
            )
            "CT-LP-3390" -> listOf(
                EventoHistorial("Disponible", "12 Jul 2026", "2026-07-12", "Admin. Carlos Ruiz", "Equipo nuevo verificado y marcado como DISPONIBLE en Almacén Central, listo para asignación."),
                EventoHistorial("Registrado", "08 Jul 2026", "2026-07-08", "Ingreso inicial al sistema", "Equipo dado de alta en el inventario. Configuración inicial completada.")
            )
            else -> emptyList()
        }
    }
}
