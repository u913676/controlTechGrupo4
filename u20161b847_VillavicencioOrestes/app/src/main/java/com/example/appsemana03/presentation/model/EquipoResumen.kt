package com.example.appsemana03.presentation.model

// Tipo de equipo (define el ícono que se muestra en la card).
enum class TipoEquipo { LAPTOP, WORKSTATION, IMPRESORA }

// Datos que se muestran en la card de la lista de equipos.
data class EquipoResumen(
    val id: String,
    val nombre: String,
    val modelo: String,
    val estado: String,   // "Disponible" | "Asignado" | "Reparación" | "Devuelto"
    val detalle: String,  // ubicación o responsable
    val tipo: TipoEquipo
)
